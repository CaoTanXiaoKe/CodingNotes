import java.io.*; 
import java.net.*; 
public class MultiTalkServer {
				static int clientnum = 0; 						// 静态成员变量, 记录当前客户的个数
				public static void main(String args[ ] ) throws IOException
				{
					ServerSocket serverSocket = null;
					boolean listening = true; 
					try
					{
						// 创建一个 ServerSocket 在端口 4700 监听客户请求
						serverSocket = new ServerSocket(4700); 
					}catch(IOException e)
					{
						System.out.println("Could not listen on port: 4700"); 
						System.exit(-1); 													// 退出
					}
					while(listening)
					{
						// 监听到客户请求, 根据得到的Socket对对象和客户计数创建并启动服务线程
						new ServerThread(serverSocket.accept(), clientnum).start();
						clientnum++; 
					}
					serverSocket.close(); 													// 关闭ServerSocket 
				}
				
}

 class ServerThread extends Thread{
	Socket  socket = null;								// 保存与本线程相关的Socket 对象 
	int clientnum;  											// 保存本线程的客户计数 
	public ServerThread(Socket socket, int num)		// 构造方法
	{																								
		this.socket = socket; 													// 初始化socket变量								
		clientnum = num + 1; 													// 初始化 clientnum 变量 
	}
	
	public void run()									// 线程主体
	{
		try
		{
			String line; 
			// 由 Socket 对象得到输入流, 并构造相应的BufferedReader 对象. 
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
			// 由Socket对象得到输出流, 并构造PrintWriter对象
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			// 由系统标准输入设备构造BufferedReader对象 
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in)); 
			// 在显示屏上输出从客户端读入的字符串 
			System.out.println("Client: " + clientnum + is.readLine()); 
			// 从标准输入读入一字符串
			line = sin.readLine(); 
			while(!line.equals("bye"))
			{
				os.println(line); 
				os.flush();
				// 在显示屏上输出该字符串
				System.out.println("Server: " + line); 
				// 从Client 读入一字符, 并输入到显示屏上
				System.out.println("Client: " + clientnum + is.readLine()); 
				line = sin.readLine(); 											// 从系统标准输入读入一字符串
			} // 继续循环
			os.close(); 																		// 关闭Socket输出流
			is.close(); 																			// 关闭Socket输入流
			socket.close();																// 关闭Socket
		}catch(Exception e) 
		{
			System.out.println("Error: " + e); 						// 在显示屏上输出错误信息.		
		}
	}

}
