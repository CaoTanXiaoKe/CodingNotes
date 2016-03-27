import java.awt.*; 
import java.applet.*; 
public class DispGraph extends Applet 
{
	int g_width, g_height; 				// 图像大小
	Image img; 							// 定义一个图像对象
	public void init()					// 装载图像并产生图像对象
	{
		img = getImage(getDocumentBase(), "Images/nephew.jpg"); 
		g_width = Integer.parseInt(getParameter("g_width")); 	// 获得参数
		g_height = Integer.parseInt(getParameter("g_height")); 
	}
	public void paint(Graphics g)
	{
		g.drawImage(img, 0, 0, g_width, g_height, this);  // 绘制 Image 对象
		play(getDocumentBase(), "Music/BMZ"); 		// 播放声音文件
	}
}