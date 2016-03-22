public class NestingException
{
	public static void main(String[ ] args)
	{
		int a, b, c;
		a = 67; b = 0; 
		try
		{
			 c = a/b; 
			 System.out.println( a + "/" + b + "=" + c); 
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
	}
}