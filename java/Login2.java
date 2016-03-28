
import java.awt.*; 

public class Login2 {
	public static void main(String[ ] args)
	{
		Frame f = new Frame("User Login");
		f.setSize(280, 150);
		Button b = new Button("login"); 
		f.add(b);
		f.setBackground(Color.lightGray);
		f.setVisible(true);
		f.pack();
	}
}
