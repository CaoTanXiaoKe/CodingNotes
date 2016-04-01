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
		final String[ ] columnNames =  {"����", "ְ��", "�绰", "��н", "���"}; 
		// ����и��е����ݱ����ڶ�ά���� data ��
		final Object[][] data = 
			{
					{"����", "�ܾ���", "01068790231", new Integer(5000), new Boolean(false)}, 
					{"���", "����", "01069785321", new Integer(3500), new Boolean(true)}, 
					{"����", "����", "01065498732", new Integer(4500), new Boolean(false)}, 
					{"����", "����", "10162796879", new Integer(2000), new Boolean(true)}, 
					{"����", "����", "1063541298", new Integer(4000), new Boolean(false)}
			}; 
		// �������
		JTable table = new JTable(data, columnNames); 
		// ���������������
		this.setSize(new Dimension(400, 130)); // �趨���ڵĿ��Ϊ 400�� �߶�Ϊ 130 
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

// ��� ��ע�͵���δ��룬 frame.pack() ���������Ĵ�С�������ڴ�С�� 