import java.awt.*;

public class FlowLayoutDemo extends Frame{
	public FlowLayoutDemo(String title)
	{
		super(title); 
	}
	public static void main(String[ ] args)
	{
		FlowLayoutDemo fs = new FlowLayoutDemo("Border Layout Simple");
		fs.setLayout(new FlowLayout());		//采用布局管理器 FlowLayout() 进行布局。 
		fs.add(new Button("one")); 
		fs.add(new Button("two")); 
		fs.add(new Button("three")); 
		fs.add(new Button("four")); 
		fs.setSize(200, 200);
		fs.setVisible(true); 
	}
}
