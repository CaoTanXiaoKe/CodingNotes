import java.awt.*;
/**
 * Title: GridBagLayout���ֹ�����<br>
 * Description�� GridBagLayout��������ԣ� CridBagLayoutͨ���� GridBagConstraints�İ�����������Ƶ�
 * ��ͼ�ı�����Ĵ�С�� �����ǰ��������ϣ���ڷŵ�λ�á�<br>
 * @author ChenWenKe
 * @version 1.0
 */
public class GridBagLayoutDemo {
	public static void main(String[] args)
	{
		Frame gd = new Frame("GridBagLayoutDemo"); 
		gd.setSize(300, 100);	// ���ô��ڵĴ�С
		// ʹ���� GridBagConstriants
		GridBagConstraints gbc = new GridBagConstraints();
		// �趨��۹�����ΪGridBagLayout��۹�����
		gd.setLayout(new GridBagLayout());
		// ���еİ�ť����ѷ����ʣ��ռ�����
		gbc.fill = GridBagConstraints.BOTH; 
		gbc.gridwidth = 1; 			// ���õ�һ����ť��ʾ����
		gbc.gridheight = 1; 
		Button button1 = new Button("��");
		//
		((GridBagLayout)gd.getLayout()).setConstraints(button1, gbc);
		gd.add(button1);
		// ���õڶ�����ť��gridwidth, gridheight ���ֲ���
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		Button button2 = new Button("��"); 
		((GridBagLayout)gd.getLayout()).setConstraints(button2, gbc);
		gd.add(button2);
		gbc.gridheight = 4; 				// ���õ�������ť��ʾ����
		gbc.gridwidth = 1; 
		Button button3 = new Button("��");
		((GridBagLayout)gd.getLayout()).setConstraints(button3, gbc);
		gd.add(button3);
		gbc.gridheight = 2; 				// ��ʾ���ĸ���ť����ʾ����
		gbc.gridwidth = 1; 
		Button button4 = new Button("��"); 
		((GridBagLayout)gd.getLayout()).setConstraints(button4, gbc);
		gd.add(button4);
		gbc.gridwidth = GridBagConstraints.REMAINDER; // ���õ������ť�����������У�
		Button button5 = new Button("��"); 
		((GridBagLayout)gd.getLayout()).setConstraints(button5, gbc);
		gd.add(button5);
		gbc.insets = new Insets(5, 6, 7, 8); // ���Ʊ߾�(�ϣ� �� �£� ��)�� ���õ�������ť
		Button button6 = new Button("GridBagLayDemo"); 
		((GridBagLayout)gd.getLayout()).setConstraints(button6, gbc);
		gd.add(button6); 
		gd.setVisible(true);
		
	}
}
