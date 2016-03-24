class Account
{
	volatile private int value;
	void put(int i)
	{
		value = value + i; 
		System.out.println("存入： " + i + "账上金额为：" + value);
	}
	int get(int i)
	{
		if (value > i)
		{
			value = value - i; 
		}
		else
		{
			i = value;
			value = 0; 
		}
		System.out.println("取走" + i + "账上金额为：" + value);
		return i; 
	}
	
}

class Save implements Runnable
{
	private Account a1;
	public Save(Account a1)
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
	private Account a1; 
	public Fetch(Account a1)
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
public class TestCommunicate {
	public static void main(String[ ] args)
	{
		Account a1 = new Account(); 
		new Thread(new Save(a1)).start();
		new Thread(new Fetch(a1)).start();
		
	}
}
