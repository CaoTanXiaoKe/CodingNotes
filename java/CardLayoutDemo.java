import java.awt.*;
// CardLayout������ĺ�������˿���һ���� ����ֻ��ʾ��ǰ���һ�� ��
public class CardLayoutDemo {
	static Frame frm = new Frame("Card layout"); 
	public static void main(String[ ] args)
	{
		CardLayout card = new CardLayout(); 
		frm.setLayout(card);
		frm.setSize(200, 150);
		frm.add(new Button("Button 1"), "c1");
		frm.add(new Button("Button 2"), "c2"); 
		frm.add(new Button("Button 3"), "c3"); 
		frm.add(new Button("Button 4"), "c4"); 
		card.show(frm, "c3");
		frm.setVisible(true);
		
	}
}
