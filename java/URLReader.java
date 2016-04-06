import java.net.*;
import java.io.*;
// 用URL 访问 WWW资源 
// 在屏幕上显示出一个网页的HTML内容. 
public class URLReader {
	public static void main(String[ ] args) throws Exception  // 声明抛出所有例外 
	{
		URL tirc = new URL("http://www.chd.edu,cn/"); 				// 构建一URL对象 
		// 使用openStream 得到一输入流并由此构造一个 BufferedReader 对象. 
		BufferedReader in = new BufferedReader(new InputStreamReader(tirc.openStream())); 
		String inputLine; 
		// 从输入流不断地读数据, 直到读完为止
		while((inputLine = in.readLine()) != null)
		{
			System.out.println(inputLine);
		}
		in.close(); 
	}
}
