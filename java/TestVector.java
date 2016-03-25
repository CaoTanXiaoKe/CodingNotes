import java.util.*; // �õ���Vector���Enumeration�ӿڶ��ڴ˰���
/**
 * Title�� Vector����Enumeration��Iterator�ӿ�Ӧ�á�<br> 
 * Description: ��Vector����������Ԫ�صĺ͡� <br>
 * @author ChenWenKe
 * @version 1.0
 */
public class TestVector {
	public static void main(String[ ] args)
	{
		int b = 0; 
		Vector v = new Vector(); 
		System.out.println("Please Enter Number: ");
		while(true)
		{
			try
			{
				b = System.in.read();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			if(b == '\r' || b == '\n')
			{
				break; 
			}
			else
			{
				int num = b - '0'; 
				v.addElement(new Integer(num));
			}
		}
		int sum = 0; 
		/*
		Enumeration e = v.elements(); 
		while(e.hasMoreElements())
		{
			Integer intObj = (Integer)e.nextElement(); 
			sum += intObj.intValue(); 
		}
		*/
		// �ⲿ�ִ����Ǻ�����ע�͵��Ĵ����������һ���ġ� 
		Iterator itr = v.iterator(); 
		while(itr.hasNext())
		{
			Integer intObj = (Integer)itr.next(); 
			sum += intObj.intValue(); 
		}
		System.out.println(sum);
	}
}
