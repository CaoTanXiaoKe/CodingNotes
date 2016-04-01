import java.awt.*;
import javax.swing.*; 
public class TableDemoMVC extends JFrame
{
	TableDemoMVC()
	{
		init(); 
	}
	protected void init()
	{
		Container ct; 
		final String[ ] columnNames =  {"姓名", "职称", "电话", "月薪", "婚否"}; 
		// 表格中各行的内容保存在二维数组 data 中
		final Object[][] data = 
			{
					{"王东", "总经理", "01068790231", new Integer(5000), new Boolean(false)}, 
					{"李宏", "秘书", "01069785321", new Integer(3500), new Boolean(true)}, 
					{"李瑞", "开发", "01065498732", new Integer(4500), new Boolean(false)}, 
					{"赵新", "保卫", "10162796879", new Integer(2000), new Boolean(true)}, 
					{"陈理", "销售", "1063541298", new Integer(4000), new Boolean(false)}
			}; 
		// 创建表格
		JTable table = new JTable(data, columnNames); 
		// 将表格加入滚动窗口
		this.setSize(new Dimension(400, 130)); // 设定窗口的宽度为 400， 高度为 130 
		JScrollPane jp = new JScrollPane(table); 
		ct = getContentPane(); 
		ct.add(jp, BorderLayout.CENTER); 
	}
	public static void main(String[] args)throws ClassNotFoundException, 
												InstantiationException,
												IllegalAccessException, 
												UnsupportedLookAndFeelException 
												{
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		TableDemoMVC frame = new TableDemoMVC(); 
		// frame.pack();  the description goto line 42
		frame.setVisible(true);
												}
}

// 如果 不注释掉这段代码， frame.pack() 会根据组件的大小调整窗口大小。 