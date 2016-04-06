import java.io.*;

public class inDateSortMaxMinIn {
	public static void main(String args[ ])
	{
		try
		{
			BufferedReader keyin = new BufferedReader(new InputStreamReader(System.in)); 
			String c1;
			int i = 0; 
			int [ ] e = new int[10]; 
			while (i < 10)
			{
				try
				{
					c1 = keyin.readLine(); 
					e[i] = Integer.parseInt(c1); 
					i++; 
				}
				catch(NumberFormatException ee)
				{
					System.out.println("请输入正确的数字!"); 
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("系统有错误"); 
		}
	}
}
