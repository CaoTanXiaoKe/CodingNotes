// 程序目的： 演示子类对象实例化过程

}class Pare{
	int i = 3;
	Pare(){}; 
};
class Construct extends Pare{
	int i = 8;
	Construct() {}
	Construct(int num){ this(); } // 调用默认构造函数
	int getSuper() { return super.i; }  
	public static void main(String[] args)
	{
		Construct ct = new Construct(9); // 调用的是默认的构造函数
		System.out.println(ct.i); 
		System.out.println(ct.getSuper()); 
	}

/*
程序输出： 
8 
3 
*/
