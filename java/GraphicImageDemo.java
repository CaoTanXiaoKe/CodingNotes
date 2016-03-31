import java.awt.*;
import java.awt.event.*;

public class GraphicImageDemo extends Frame{
	private Image img;
	public void init() throws Exception
	{
		setBounds(100, 100, 230, 230); 
		img = getToolkit().getImage("earth.jpg"); 
		Thread.sleep(3000);
		setVisible(true); 
		addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				}); 
	}
	public void paint(Graphics g)
	{
		//if (img != null)
		{
			g.drawImage(img, 40, 40, this); 
		}
	}
	public static void main(String arg[]) throws Exception
	{
		GraphicImageDemo f = new GraphicImageDemo(); 
		f.init(); 
	}
}
