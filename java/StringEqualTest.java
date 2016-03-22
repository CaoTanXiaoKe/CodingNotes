public class StringEqualTest
{
	public static void main(String[ ] args)
	{
		String s = new String("Hello"); 
		String t = new String("Hello"); 
		if (s == t)	// == 比较的是内存中的引用地址.
		{
			System.out.println("相等"); 
		}
		else 
		{
			System.out.println("不相等"); 
		}
	}
}
