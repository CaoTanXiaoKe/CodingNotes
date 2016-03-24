/**
 * Title： 同步通信问题的解决
 * Description：   用  notifyAll（）唤醒多条同级线程。 
 * @author ChenWenKe
 * @version 1.0 
 */

class Account1
{
	volatile private int value; 
	volatile private boolean isMoney = false; 
	synchronized void put(int i) // 
	{
		if(isMoney)
		{
			try{ wait(); }
			catch(Exception e) { }
		}
		value = value + i; 
		System.out.println("存入： " + i + " 账上金额为：" + value); 
		isMoney = true; 
		notifyAll();  // 释放该对象监视权，并唤醒等待队列里所有同级线程。 
					  // 
	}
	synchronized int get(int i)
	{
		if(!isMoney)
		{
			try{ wait(); }
			catch(Exception e) { }
		}
		if (value > i)
		{
			value = value - i; 
		}
		else 
		{
			i = value; 
			value = 0; 
		}
		System.out.println("取出: " + i + " 账上金额为: " + value); 
		isMoney = false; 
		notifyAll();
		return i; 
		
	} 
}


class Save implements Runnable
{
	private Account1 a1;
	public Save(Account1 a1)
	{
		this.a1 = a1; 
	}
	public void run()
	{
		while(true)
		{
			a1.put(100);
		}
	}
}

class Fetch implements Runnable
{
	private Account1 a1; 
	public Fetch(Account1 a1)
	{
		this.a1 = a1; 
	}
	public void run()
	{
		while(true)
		{
			a1.get(100); 
		}
	}
}
public class TestCommunicate3 {
	public static void main(String[ ] args)
	{
		Account1 a1 = new Account1(); 
		new Thread(new Save(a1)).start();    // 两个线程存
		new Thread(new Save(a1)).start();
		new Thread(new Fetch(a1)).start();   // 一个线程取 
		
	}
}
