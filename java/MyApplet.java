//接口在java事件处理机制中的应用 
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
public class MyApplet extends Applet implements ActionListener{
	private TextField input;
	private double d = 0.0; 
	// 进行初始化工作, 产生对象, 加入监听者 
	public void init(){
		input = new TextField(10); 
		// MyApplet 是容器, input是组件, 调用add使input嵌入容器
		// 否则对象input即使创建, 也无法在界面中"看到" 
		add(input); 
		// 本类对象作为监听者身份进行注册, 加入input当中 
		input.addActionListener(this); 
	}
	public void paint(Graphics g)
	{
		g.drawString("您输入了数据" + d, 10, 50); 
	}
	public void actionPerformed(ActionEvent e)
	{	// 首先得到double类的对象, 之后调用对象方法doubleValue得到值
		d = Double.valueOf(input.getText()).doubleValue(); 
		// 进行刷新, 调用paint()方法 
		repaint(); 
	}
}
