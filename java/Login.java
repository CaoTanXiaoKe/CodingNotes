import java.awt.*;
public class Login {
	public static void main(String[] args)
	{
		Frame f = new Frame("User Login"); 
		f.setSize(280, 150);
		f.setLayout(null); 
		Button b = new Button("login"); 
		b.setBounds((f.getWidth()-100)/2, (f.getHeight()-50)/2, 100, 50);
		f.add(b);
		f.setBackground(Color.lightGray);
		f.setVisible(true);
	}
}
