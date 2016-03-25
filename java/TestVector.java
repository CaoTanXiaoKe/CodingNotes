import java.util.*; // 用到的Vector类和Enumeration接口都在此包中
/**
 * Title： Vector类与Enumeration和Iterator接口应用。<br> 
 * Description: 求Vector容器中所有元素的和。 <br>
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
		// 这部分代码是和上面注释掉的代码的作用是一样的。 
		Iterator itr = v.iterator(); 
		while(itr.hasNext())
		{
			Integer intObj = (Integer)itr.next(); 
			sum += intObj.intValue(); 
		}
		System.out.println(sum);
	}
}
