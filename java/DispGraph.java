import java.awt.*; 
import java.applet.*; 
public class DispGraph extends Applet 
{
	int g_width, g_height; 				// ͼ���С
	Image img; 							// ����һ��ͼ�����
	public void init()					// װ��ͼ�񲢲���ͼ�����
	{
		img = getImage(getDocumentBase(), "Images/nephew.jpg"); 
		g_width = Integer.parseInt(getParameter("g_width")); 	// ��ò���
		g_height = Integer.parseInt(getParameter("g_height")); 
	}
	public void paint(Graphics g)
	{
		g.drawImage(img, 0, 0, g_width, g_height, this);  // ���� Image ����
		play(getDocumentBase(), "Music/BMZ"); 		// ���������ļ�
	}
}