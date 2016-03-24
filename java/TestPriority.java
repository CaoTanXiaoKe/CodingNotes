class Resource implements Runnable
{
	volatile public int i;
	public Resource(int _i)
	{
		i = _i; 
	}
	public void run()
	{
		while(true)
		{
			if(i > 0)
			{
				i--; 
				System.out.println(Thread.currentThread().getName() + " " + i); 
			}
			else 
			{
				System.out.println(Thread.currentThread().getName()); 
				break; 
			}
		}
	}
}

public class TestPriority
{
	public static void main(String[ ] args)
	{
		Resource m = new Resource(100); 
		Thread t1 = new Thread(m); 
		Thread t2 = new Thread(m); 
		t2.setPriority(Thread.MAX_PRIORITY); 
		t1.start(); 
		try
		{
			Thread.sleep(5); 
		}
		catch(Exception e) { }
		t2.start(); 
	}
}