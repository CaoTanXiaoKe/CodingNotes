/**
* Title: 委托事件模型<br>
* 
*/
import java.awt.*;
import java.awt.event.*; 
class MyWindowListener implements WindowListener{
	public void windowClosing(WindowEvent e)
	{
		System.out.println("ÎÒÍË³öÁË£¡");
		e.getWindow().setVisible(false); // ±äÎª²»ÏÔÊ¾
		((Window)e.getComponent()).dispose();// ÊÍ·Å´°¿Ú×ÊÔ´
		System.exit(0);
	}
	public void windowActivated(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
	
}; 
class TestFrame1
{
	public static void main(String args[])
	{
		Frame f = new Frame("A Test Window"); 
		f.setSize(250, 150);
		f.setVisible(true);
		f.addWindowListener(new MyWindowListener());
	}
}

