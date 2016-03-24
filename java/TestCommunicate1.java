/**
 * Title: 同步通信<br>
 * Description: 利用 synchronized 和 notify（）实现线程同步
 * @author CaoTanXiaoKe
 * @version 1.0 
 */
class Account1
{
	volatile private int value; 
	volatile private boolean isMoney = false; 
	synchronized void put(int i) // 这里用于同步
	{
		if(isMoney)
		{
			try{ wait(); }
			catch(Exception e) { }
		}
		value = value + i; 
		System.out.println("存入" + i + " 账上金额为: " + value); 
		isMoney = true; 
		notify();  // 释放该对象的监视权并唤醒队列中和它拥有同类型对象监视器标签
						// 的等待线程
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
		System.out.println("取走: " + i + " 账上金额为: " + value); 
		isMoney = false; 
		notify();
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
public class TestCommunicate1 {
	public static void main(String[ ] args)
	{
		Account1 a1 = new Account1(); 
		new Thread(new Save(a1)).start();
		new Thread(new Fetch(a1)).start();
		
	}
}
