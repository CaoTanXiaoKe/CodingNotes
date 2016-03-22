// 自定义异常与异常对象的创建
public class SelfGenerateException extends Exception
{
	SelfGenerateException(String msg)
	{
		super(msg);		//  调用Exception的构造方法 
	}
	static void throwOne() throws SelfGenerateException
	{
		int a = 1;
		if (a == 1)		// 如果 a 为 1 就认为在特定应用下存在异常, 改变执行路径, 抛出异常
		{
			throw new SelfGenerateException(" a 为 1"); 
		}
	}
	public static void main(String[ ] args)
	{
		try
		{
			throwOne(); 
		}
		catch(SelfGenerateException e)
		{
			e.printStackTrace(); 
		}
	}
}
