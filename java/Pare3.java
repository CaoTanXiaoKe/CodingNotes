// use: a instancof A , 其中a为对象的引用， A为类。 如果 a 为 A 的实例， 或者 A 子类的实例， 则返回true
// 如果 a 为 A 的父类的实例， 则返回false； 如果 a对象 与 A类 没有关系， 则编译通不过。 

class Uncle{ }
class Pare { }
class Pare1 extends Pare{ }
class Pare2 extends Pare1{ }
class Pare3 {
	public static void main(String[] args)
	{
		Uncle u = new Uncle(); 
		Pare p = new Pare(); 
		Pare1 p1 = new Pare1(); 
		Pare2 p2 = new Pare2(); 
		if (p instanceof Pare) 
		{
			System.out.println("p instanceof Pare"); 
		}
		if (!(p1 instanceof Pare)) 
		{
			System.out.println("p1 not instanceof Pare"); 
		}
		else 
		{
			System.out.println("p1 instanceof Pare"); 
		}
		if (p2 instanceof Pare) 
		{
			System.out.println("p2 instanceof Pare"); 
		}
		if (p1 instanceof Pare1) 
		{
			System.out.println("p1 instanceof Pare1"); 
		}
		if (p2 instanceof Pare1) 
		{
			System.out.println("p2 instanceof Pare1"); 
		}
		if (p1 instanceof Pare2) 
		{
			System.out.println("p1 instanceof Pare2"); 
		}
		else 
		{
			System.out.println("p1 not instanceof Pare2"); 
		}
		/*          如果不注释掉这段代码， 编译不能通过。 
		if (p instanceof Uncle) 
		{
			System.out.println("p instanceof Uncle"); 
		}
		else 
		{
			System.out.println("p not instanceof Uncle"); 
		}
		*/
		if (null instanceof String)
		{
			System.out.println("null instanceof String"); 
		}
		else 
		{
			System.out.println("null not instanceof String"); 
		}
	}
}