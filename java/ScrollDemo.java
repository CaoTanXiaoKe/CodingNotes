import java.awt.*;
import java.awt.event.*;

public class ScrollDemo {
	public static void main(String[ ] args)
	{
		Frame f = new Frame("ScrollPanel"); 	// ����һ��������ScrollPanel�Ĵ���
		// ����һ��TestArea�Ķ��� û�й�����
		TextArea t = new TextArea("", 10, 50, TextArea.SCROLLBARS_NONE);
		ScrollPane sp = new ScrollPane(); 		// ����һ��������� sp
		sp.add(t); 								// �� t ���뵽 sp �� 
		f.add(sp); 								// �� sp ���뵽 f ��
		f.pack();								// �������ڵĴ�С��Ӧ sp
		f.setVisible(true);					// ��������Ϊ�ɼ�
	}
}
