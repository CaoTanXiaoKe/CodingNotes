import java.io.*;
public class CharCode
{
	public static void printByteArray(String msg, byte[ ] t)
	{
		System.out.println(msg + "**************************"); 
		for (int i = 0; i < t.length; i++)
		{
			System.out.println(Integer.toHexString(t[i])); 
		}
	}
	public static void printCharArray(String msg, char[ ] c)
	{
		System.out.println(msg + "************************"); 
		for(int i = 0; i < c.length; i++)
		{
			System.out.println(Integer.toHexString(c[i])); 
		}
	}
	public static void main(String[ ] args)
	{
		try
		{
			String str  = "中文"; 
			System.out.println(str); 
			// unicode 字符集中"中文"二字的对应代码
			printCharArray("unicode: ",str.toCharArray()); 
			//转为本地字符集 GB2312 对应的代码
			byte[ ] b = str.getBytes("GB2313"); 
			printByteArray("GB2313", b); 
			// 转为ISO8859-1对应的代码, 因为ISO8859-1是英文字符集
			// 没有对应的汉字代码, 所以转化错误.
			byte[ ] m = str.getBytes("IS08859-1"); 
			printByteArray("ISO8859-1", m); 
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println("没有相应的字符集!"); 
		}
	}
}
