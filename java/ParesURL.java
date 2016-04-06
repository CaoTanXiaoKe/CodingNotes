import java.net.*; 
import java.io.*; 
// 生成一个URL对象, 并获取它的各个属性. 
public class ParesURL {
	public static void main(String[ ] args) throws Exception
	{
		URL Aurl = new URL("http://java.sun.com:80/doc/books/"); 
		URL tuto = new URL(Aurl, "tutorial.intro.html#DOWNLOADING"); 
		System.out.println("protocol =  " + tuto.getProtocol()); 
		System.out.println("host = " + tuto.getHost()); 
		System.out.println("filename = " + tuto.getFile()); 
		System.out.println("port = "  + tuto.getPort()); 
		System.out.println("ref = " + tuto.getRef()); 
		System.out.println("query = " + tuto.getQuery()); 
		System.out.println("path = " + tuto.getPath()); 
		System.out.println("UserInfo = " + tuto.getUserInfo()); 
		System.out.println("Autority = " + tuto.getAuthority()); 
	}
}
