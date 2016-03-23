public class MyThread extends Thread 
{
	public static void main(String args[ ])
	{
		Thread a = new MyThread(); 
		a.start(); 
		System.out.println("This is main thread."); 
	}
	public void run()
	{
		System.out.println("This is another thread."); 
	}
}