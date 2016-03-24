class Resource implements Runnable
{
	volatile public int i; 
	volatile public Integer it; 
	public Resource(int _i)
	{
		i = _i; 
		it = new Integer(i); 
	}
	public void run()
	{
		while(true)
		{
			synchronized(it)
			{
				if(i > 0)
				{
					try 
					{
						Thread.sleep(500); 
					}
					catch(Exception e){ }
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
}

public class TestSecurity1
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