import java.awt.*;
import java.awt.event.*;

public class TestFrameThree extends WindowAdapter {
	public static void main(String arg[ ])
	{
		Frame f = new Frame("A Test Window");
		f.setSize(250, 150);
		f.setVisible(true);
		f.addWindowListener(new TestFrameThree());
	}
	
	public void windowClosing(WindowEvent e)
	{
		System.out.println("Exit!");
		e.getWindow().setVisible(false);
		((Window)e.getComponent()).dispose();
		System.exit(0);
	}
	
}
