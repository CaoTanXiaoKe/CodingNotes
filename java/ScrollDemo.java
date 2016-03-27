import java.awt.*;
import java.awt.event.*;

public class ScrollDemo {
	public static void main(String[ ] args)
	{
		Frame f = new Frame("ScrollPanel"); 	// 创建一个标题是ScrollPanel的窗口
		// 生成一个TestArea的对象， 没有滚动条
		TextArea t = new TextArea("", 10, 50, TextArea.SCROLLBARS_NONE);
		ScrollPane sp = new ScrollPane(); 		// 生成一个滚动面板 sp
		sp.add(t); 								// 将 t 加入到 sp 中 
		f.add(sp); 								// 将 sp 加入到 f 中
		f.pack();								// 调整窗口的大小适应 sp
		f.setVisible(true);					// 将窗口设为可见
	}
}
