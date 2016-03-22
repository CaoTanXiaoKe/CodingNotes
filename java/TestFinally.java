// finally 是 try -- catch -- finally 语句块的出口, 用来处理一些善后工作
// 它是可选的部分,但是一旦选定, 必定执行! 
public class TestFinally
{
	public static void main(String[ ] args)
	{
		try
		{
			return; 
		}
		finally 
		{
			System.out.println("Finally"); 
		}
	}
}
