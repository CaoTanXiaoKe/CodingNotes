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
			if (i > 0)
			{
				try                    
				{
					Thread.sleep(200); // 当线程 - 0 进入 睡眠时， 线程 - 1 继续也进入 这里， 使得 i 连续减两次
					                   // 由于这种原因， 即使没有睡眠， 偶然情况下仍可能发生。 
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

public class TestSecurity
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