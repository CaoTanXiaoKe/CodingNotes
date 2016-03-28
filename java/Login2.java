
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
		f.pack();// 因为login按钮有默认大小，Frame大小不确定，调用Frame对象的pack方法， 使得Frame按照组件当前的合适的尺寸（preferred size）进行相应的调整。 
	}
}
