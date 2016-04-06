import java.io.*;

public class Input1 {
	public static void sumChar(byte[] b)   // ×ÖÄ¸Í³¼Æ
	{
		int n = 0; 
		for (int i = 0; i < b.length; i++)
			if(b[i]>='a'&&b[i]<='z') n++; 
		System.out.println("char count=" + n);
	}
	public static void main(String args[])throws IOException
	{
		System.out.println("Input: ");
		byte buffer[] = new byte[512]; 
		int count = System.in.read(buffer);
		System.out.println("Output: ");
		for (int i = 0; i < count; i++)
			System.out.print(" " + buffer[i]);
		System.out.println();
		for(int i = 0; i < count; i++)
			System.out.print((char)buffer[i]);
		System.out.println("count = " + count);
		sumChar(buffer);
	}
}
