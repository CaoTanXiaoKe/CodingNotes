import java.awt.*; 
import java.awt.event.*;

public class Calc1 implements ActionListener{
	Frame f;
	TextField tf1;
	Button b1, b2, b3, b4;
	public void display()
	{
		f = new Frame("Calculation"); 
		f.setSize(260, 150);
		f.setLocation(320, 240);
		f.setBackground(Color.lightGray);
		f.setLayout(new FlowLayout(FlowLayout.LEFT));
		tf1 = new TextField(30); 
		tf1.setEditable(false);
		f.add(tf1);
		b1 = new Button("1"); 
		b2 = new Button("2"); 
		b3 = new Button("+"); 
		b4 = new Button("c"); 
// 		b5 = new Button("")
		f.add(b1);
		f.add(b2); 
		f.add(b3); 
		f.add(b4); 
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		f.addWindowListener(new WinClose());
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == b4)
		{
			tf1.setText("");
		}
		else
		{
			tf1.setText(tf1.getText() + e.getActionCommand());
		}
	}
	public static void main(String[] arg)
	{
		(new Calc1()).display();
	}
	
}

class WinClose extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
}