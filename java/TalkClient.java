import java.io.*;
import java.net.*; 

public class TalkClient {
	public static void main(String args[ ])
	{
		try 
		{
			// 向本机的 4700 端口发出客户请求
			Socket socket = new Socket("127.0.0.1", 4700); 
			// 由系统标准输入设备构造BufferedReader 对象. 
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in)); 
			// 由Socket对象得到输出流, 并构造PrintWriter 对象. 
			PrintWriter os = new PrintWriter(socket.getOutputStream()); 
			// 由Socketd对得到输入流, 并构造相应的BufferedReader 对象. 
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			String readline; 
			readline = sin.readLine();					// 从系统标准输入上读入一字符串.  
			if(!readline.equals(""))
			{
				System.out.println(); 
			}
			// 若读入字符串为bye则停止循环 
			while(!readline.equals("bye"))
			{
				// 将读入的字符串输出到Server 
				os.println(readline); 
				os.flush(); 
				// 在显示屏上输出读入的字符串
			//	System.out.println("草滩小王子:  " + readline); 这里已经因输入出现在了屏幕上
				System.out.println(); 
				// 从 Server 读入一字符串, 并输出到显示屏上
				System.out.println("拉面女神: " + is.readLine()); 
				readline = sin.readLine(); 							// 从系统标准输入读取下一字符串 
			} // 继续循环 
			os.close(); 														//	关闭Socket 输出流
			is.close(); 															// 关闭Socket输入流
			socket.close(); 												// 关闭Socket 
		} catch(Exception e)
		{
			System.out.println("Error" + e); 				// 在显示屏上输出错误信息
		}
	}
}
