abstract class Anonynity
{
	abstract public void fun1(); 
}; 
public class Outer1
{
	public void callInner(Anonynity a)
	{
		a.fun1(); 
	}
	public static void main(String[ ] args)
	{
		class inner extends Anonynity
		{
			public void fun1()
			{
				System.out.println("匿名类测试"); 
			}
		}; 
		new Outer1().callInner(new inner()); 
	}
}
