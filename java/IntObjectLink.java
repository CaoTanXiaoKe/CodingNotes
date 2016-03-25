import java.util.*;

public class IntObjectLink {
	public static void main(String[ ] args)
	{
		Integer tem;
		try
		{
			LinkedList lst = new LinkedList(); 
			for(int i = 1; i < 11; i++)
			{
				lst.addFirst(new Integer(i));
			}
			for(int i = 1; i < 11; i++)
			{
				tem = (Integer) lst.removeFirst(); 
				System.out.println(tem.intValue());
			}
		}
		catch(Exception e)
		{
			System.out.println("ÓÐ´íÎó");
		}
	}
}
