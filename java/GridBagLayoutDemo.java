import java.awt.*;
/**
 * Title: GridBagLayout布局管理器<br>
 * Description： GridBagLayout具有灵活性， CridBagLayout通过类 GridBagConstraints的帮助，按照设计的
 * 意图改变组件的大小， 把它们摆在设计者希望摆放的位置。<br>
 * @author ChenWenKe
 * @version 1.0
 */
public class GridBagLayoutDemo {
	public static void main(String[] args)
	{
		Frame gd = new Frame("GridBagLayoutDemo"); 
		gd.setSize(300, 100);	// 设置窗口的大小
		// 使用类 GridBagConstriants
		GridBagConstraints gbc = new GridBagConstraints();
		// 设定外观管理器为GridBagLayout外观管理器
		gd.setLayout(new GridBagLayout());
		// 所有的按钮都会把分配的剩余空间填满
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.gridwidth = 1; 			// 设置第一个按钮显示属性
		gbc.gridheight = 1; 
		Button button1 = new Button("东");
		//
		((GridBagLayout)gd.getLayout()).setConstraints(button1, gbc);
		gd.add(button1);
		// 设置第二个按钮的gridwidth, gridheight 保持不变
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		Button button2 = new Button("西"); 
		((GridBagLayout)gd.getLayout()).setConstraints(button2, gbc);
		gd.add(button2);
		gbc.gridheight = 4; 				// 设置第三个按钮显示属性
		gbc.gridwidth = 1; 
		Button button3 = new Button("南");
		((GridBagLayout)gd.getLayout()).setConstraints(button3, gbc);
		gd.add(button3);
		gbc.gridheight = 2; 				// 显示第四个按钮的显示属性
		gbc.gridwidth = 1; 
		Button button4 = new Button("北"); 
		((GridBagLayout)gd.getLayout()).setConstraints(button4, gbc);
		gd.add(button4);
		gbc.gridwidth = GridBagConstraints.REMAINDER; // 设置第五个按钮（填满横向列）
		Button button5 = new Button("中"); 
		((GridBagLayout)gd.getLayout()).setConstraints(button5, gbc);
		gd.add(button5);
		gbc.insets = new Insets(5, 6, 7, 8); // 控制边距(上， 左， 下， 右)； 设置第六个按钮
		Button button6 = new Button("GridBagLayDemo"); 
		((GridBagLayout)gd.getLayout()).setConstraints(button6, gbc);
		gd.add(button6); 
		gd.setVisible(true);
		
	}
}
