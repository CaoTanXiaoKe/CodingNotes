class Resource implements Runnable
{
	public int i; 
	public Resource(int _i)
	{
		i = _i; 
	}
	public void run()
	{
		while(true)
		{
			if (i > 0)
			{
				i--; 
				System.out.println(Thread.currentThread().getName() + " " + i); 
			}
			else 
			{
				break; 
			}
		}
	}
}

public class TestThread
{
	public static void main(String[ ] args)
	{
		Resource m = new Resource(100); 
		Thread t1 = new Thread(m);
		Thread t2 = new Thread(m); 
		t1.start(); 
		t2.start(); 
	}
}