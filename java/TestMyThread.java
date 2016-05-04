class MyThread extends Thread 
{
	public void run()
	{
		while(true)
		{
			System.out.println(Thread.currentThread().getName() + " is running****************"); 
		}
	}
}

public class TestMyThread
{
	public static void main(String[ ] args)
	{
	        new MyThread().start(); 
		while(true)
		{
			System.out.println("main1 thread is running"); 
		}
	}
}
