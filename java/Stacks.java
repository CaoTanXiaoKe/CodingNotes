import java.util.*; 
/**
 * Title: ջ�ļ򵥾���<br>
 * Description�� �򵥵���ջ�� ��ջ������ <br>
 * @author ChenWenKe
 * @version 1.0 
 */
public class Stacks {
	static String[] months = { "January", "Februrary", "March", "April", "May", 
								"June", "July", "August", "September", "October", 
								"November", "December"}; 
public static void main(String[ ] args)
{
	Stack stk = new Stack(); 
	for(int i = 0; i < months.length; i++)
	{
		stk.push(months[i] + " "); 
	}
	System.out.println("stk=" + stk);
	stk.addElement("The last line");
	System.out.println("element 5=" + stk.elementAt(5));
	System.out.println("poping elements: ");
	while(!stk.empty())
	{
		System.out.println(stk.pop());
	}
}
}
