import java.awt.*;

public class BorderSimple extends Frame{
	public BorderSimple(String title)
	{
		super(title); 
	}
	public static void main(String[ ] args)
	{
		BorderSimple fs = new BorderSimple("Border Layout Simple"); 
		Button north = new Button("North");
		Button south = new Button("South");
		Button west = new Button("West"); 
		Button east = new Button("East"); 
		Button center = new Button("Center"); 
		fs.add(north, BorderLayout.NORTH); 
		fs.add(south, BorderLayout.SOUTH); 
		fs.add(west, BorderLayout.WEST); 
		fs.add(east, BorderLayout.EAST); 
		fs.add(center, BorderLayout.CENTER); 
		fs.setSize(400, 400); 
		fs.setVisible(true);
	}
}
