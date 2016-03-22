public class StringEqualTest1
{
	public static void main(String[ ] args)
	{
		String s = new String("Hello");
		String t = new String("Hello"); 
		if (s.equals(t))	// .equals 进行的是值比较
		{
			System.out.println("相等"); 
		}
		else 
		{
			System.out.println("不相等"); 
		}
	}
}
