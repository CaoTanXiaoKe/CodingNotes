import java.awt.*; 
import java.awt.event.*;

class MyCanvas extends Canvas
{
	private Image img; 
	private Graphics og; 
	public void init()
	{
		Dimension d = getSize(); 
		img = createImage(d.width, d.height); 
		og = img.getGraphics(); 
		og.fillRect(30, 30, 60, 60);
		og.setColor(Color.yellow);
		og.fillOval(15, 15, 30, 30);
		og.setXORMode(Color.red);
		og.fillOval(75, 75, 30, 30);
		og.setPaintMode();
		og.setColor(Color.green);
		og.fillArc(150, 40, 60, 60, 30, 160);
	}
	public void paint(Graphics g)
	{
		if(img != null)
		{
			g.drawImage(img, 20, 20, this); 
		}
	}
}; 

public class DoubleBufferDemo
{
	public static void main(String arg[])
	{
		Frame f = new Frame("DoubleBufferDemo"); 
		f.setBounds(100, 100, 250, 250);
		MyCanvas mc = new MyCanvas(); 
		f.add(mc); 
		f.setVisible(true);
		mc.init();
		mc.repaint();
		f.addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}