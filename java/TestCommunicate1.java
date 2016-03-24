/**
 * Title: ͬ��ͨ��<br>
 * Description: ���� synchronized �� notify����ʵ���߳�ͬ��
 * @author CaoTanXiaoKe
 * @version 1.0 
 */
class Account1
{
	volatile private int value; 
	volatile private boolean isMoney = false; 
	synchronized void put(int i) // ��������ͬ��
	{
		if(isMoney)
		{
			try{ wait(); }
			catch(Exception e) { }
		}
		value = value + i; 
		System.out.println("����" + i + " ���Ͻ��Ϊ: " + value); 
		isMoney = true; 
		notify();  // �ͷŸö���ļ���Ȩ�����Ѷ����к���ӵ��ͬ���Ͷ����������ǩ
						// �ĵȴ��߳�
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
		System.out.println("ȡ��: " + i + " ���Ͻ��Ϊ: " + value); 
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
