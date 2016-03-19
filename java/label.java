// java 小程序, Page107 作业12 
// 问题描述: 借助JDK帮助, 编写程序实现这样的功能: Applet当中的TextField, 每输入任一个
// 字符, 在一个label当中都能动态跟踪刷新. 
import java.applet.*; 
import java.awt.*;
import java.awt.event.*; 
public class label extends Applet implements ActionListener
{
	private TextField input; 
	private String str=""; 
	public void init()
	{
		input = new TextField(30);
		add(input); 
		input.addActionListener(this); 
	}
	public void paint(Graphics g)
	{
		g.drawString("您输入了字符串:   " + str, 25, 150); 
	}
	public void actionPerformed(ActionEvent e)
	{
		str = input.getText(); 
		repaint(); 
	}
}
