public class TestAnonymity{
	public static void main(String[] args)
	{
		Object obj = new Object()// 这里是一个匿名类, 匿名类对Object的hashCode方法进行了覆盖
		{
			public int hashCode()
			{
				return 42; 
			}
		};
		System.out.println(obj.hashCode()); 
	}
}
