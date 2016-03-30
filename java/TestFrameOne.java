import java.awt.*;
import java.awt.event.*;

public class TestFrameOne extends Frame implements WindowListener{
	public TestFrameOne(String s)
	{
		super(s); 
	}
	public TestFrameOne() {
		// TODO Auto-generated constructor stub
	}
	public void windowClosing(WindowEvent e)
	{
		System.out.println("Œ“ÕÀ≥ˆ¡À£°");
		e.getWindow().setVisible(false);
		((Window)e.getComponent()).dispose();
		System.exit(0);
	}
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String arg[])
	{
		TestFrameOne f = new TestFrameOne("A Test Window"); 
		f.setSize(250, 150);
		f.setVisible(true);
		f.addWindowListener(f); 
	}
}
