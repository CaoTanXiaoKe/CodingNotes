// 内部类
class Outer{
	private String index = "The String is Outer Class"; 
	public class Inner{
		String index = "The String is Inner Class"; 
		void print()
		{
			String index = "The String is print Method";
			System.out.println(index); 
			System.out.println(this.index); 
			System.out.println(Outer.this.index);
		}
	}
	void print()
	{
		Inner inner = new Inner(); 
		inner.print(); 
	}
	Inner getInner()
	{
		return new Inner(); 
	}
}
public class TestOutAndInner{
	public static void main(String[] args)
	{
		Outer outer = new Outer();  // 先产生外部类对象
		// Outer.Inner inner = outer.getInner(); // 内部类前没有public时的访问方法
		Outer.Inner inner = outer.new Inner(); // 利用外部类对象引用产生内部类实例
		inner.print(); 
	}
}