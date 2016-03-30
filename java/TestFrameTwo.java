import java.awt.*;
import java.awt.event.*;

public class TestFrameTwo {
	public static void main(String arg[])
	{
		Frame f  = new Frame("A Test Window"); 
		f.setSize(250, 150);
		f.setVisible(true);
		f.addWindowListener(new WindowListener()
		{
			public void windowClosing(WindowEvent e)
			{
				System.out.println("Exit");
				e.getWindow().setVisible(false);
				((Window)e.getComponent()).dispose();
				System.exit(0);
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		}); 
	}
}
