class Resource implements Runnable
{
	volatile public int i; 
	public Resource(int _i)
	{
		i = _i; 
	}
	public synchronized void run()
	{
		while(true)
		{
			if(i > 0)
			{
				try
				{
					Thread.sleep(800); 
				}
				catch(Exception e) { }
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
public class TestSecurity2
{
	public static void main(String[ ] args)
	{
		Resource m = new Resource(9); 
		Thread t1 = new Thread(m); 
		Thread t2 = new Thread(m); 
		t1.start();
		t2.start(); 
	}
}