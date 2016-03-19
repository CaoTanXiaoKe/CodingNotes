// Out1 是 Out的有名内部类编写形式. 
abstract class Anonymity
{
	abstract public void fun1(); 
}; 

public class Outer
{
	public static void main(String[] args)
	{
		new Outer().callInner(new Anonymity()
		{					//生成抽象类的匿名具体子类对象
			public void fun1()
			{
				System.out.println("匿名类测试"); 
			}
		}); 
	}
	public void callInner(Anonymity a)
	{
		a.fun1(); 
	}
}
