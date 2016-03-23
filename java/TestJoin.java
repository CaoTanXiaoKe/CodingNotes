import java.util.*; 
class Adder extends Thread
{
	int[ ] datas = null; 
	public int total = 0; 
	Adder(int[] _datas)
	{
		datas = _datas; 
	}
	public void run()
	{
		int sum = 0; 
		for(int i = 0; i < datas.length; i++)
		{
			sum += datas[i]; 
		}
		total = sum; 
	}
}; 

public class TestJoin
{
	public static void main(String[ ] args)
	{
		Random rd = new Random();  
		int[] datas = new int[10000];
		for(int i = 0; i < 10000; i++)
		{
			datas[i] = rd.nextInt(Integer.MAX_VALUE); 
		} 
		Adder a = new Adder(datas); 
		a.start(); 
		try { a.join(); }
		catch(InterruptedException it) { }
		System.out.println(a.total); 
	}
}