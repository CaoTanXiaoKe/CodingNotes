import java.io.*;
import java.net.*; 

public class TalkServer {
	public static void main(String [ ] args)
	{
		try 
		{
			ServerSocket server = null; 
			try{
				// 创建一个 ServerSocket 在端口 4700 监听客户请求 
				server = new ServerSocket(4700); 
			}catch(Exception e)
			{
				System.out.println("can not listen to :" + e); 
			}
			Socket socket = null; 
			try
			{
				socket = server.accept();				// 连接 
			}
			catch(Exception e)
			{
				System.out.println("Error. " + e); 
			}
			String line; 
			// 由 Socket 对象得到的输入流, 并构造相应的BufferReader 对象. 
			BufferedReader is = new BufferedReader ( new InputStreamReader(socket.getInputStream() ));
			// 由 Socket 对象得到输出流, 并构造PrintWriter对象. 
			PrintWriter os = new PrintWriter(socket.getOutputStream()); 
			// 由系统标准输入设备构造BufferedReader 对象. 
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			// 在显示屏上输出客户端读入的字符串
			System.out.println("Client: " + is.readLine()); 
			line = sin.readLine(); 			// 从标准输入读入一字符串
			while(!line.equals("bye")) 
			{
				os.println(line); 
				os.flush(); 
		//		System.out.println("Server: " + line); 
				System.out.println("草滩小王子: " + is.readLine()); 
				line = sin.readLine(); 
			}
			os.close(); 
			is.close(); 
			socket.close(); 
			server.close(); 
		}catch(Exception e)
		{
			System.out.println("Error: " + e); 
		}
	}
}
