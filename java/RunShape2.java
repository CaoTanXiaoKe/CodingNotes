import java.awt.*;
import java.applet.*;
abstract class Shapes{
	protected int x, y, k;
	protected double m;
	public Shapes(int x, int y, int k, double m)
	{
		this.x = x; 	this.k = k; 
		this.y = y; 	this.m = m; 
	}
	abstract public double getArea(); 
	abstract public double getPerimeter();
}

class Rect extends Shapes{
	public double getArea()
	{
		return (k*m); 
	}
	public double getPerimeter()
	{
		return (2*k + 2*m); 
	}
	public Rect(int x, int y, int width, int height)
	{
		super(x, y, width, height); 
	}
}

class Triangle extends Shapes{
	public double getArea()
	{
		return(Math.sqrt(m*(m-k)*(m-x)*(m-k))); 
	}
	public double getPerimeter()
	{
		return (k + x + y); 
	}
	public Triangle(int baseA, int baseB, int baseC)
	{
		super(baseA, baseB, baseC, 0); 
		m = (baseA + baseB + baseC)/2.0; 
	}
}

class Circle extends Shapes{
	public double getArea()
	{
		return (m*m*Math.PI); 
	}
	public double getPerimeter()
	{
		return 2*m*Math.PI; 
	}
	public Circle(int x, int y, int width)
	{
		super(x, y, width, width/2.0); 
	}
}

public class RunShape extends Applet 
{
	Rect rect = new Rect(5, 15, 25, 25); 
	Triangle tri = new Triangle(5, 5, 8);
	Circle cir = new Circle(13, 90, 25); 
	// 增加两个方法， 注意抽象类对象的声明为 s，参数 a 与 b 为字符串输出的坐标
	// getClass.getName将得到对象的类名称.  
	private void drawArea(Graphics g, Shapes s, int a, int b)
	{
		g.drawString(s.getClass().getName() + "Area" + s.getArea(), a, b); 
	}
	private void drawPerimeter(Graphics g, Shapes s, int a, int b) 
	{
		g.drawString(s.getClass().getName() + "Perimeter" + s.getPerimeter(), a, b); 
	}
	public void paint(Graphics g)
	{
		g.drawRect(rect.x, rect.y, rect.k, (int)rect.m); 
		//g.drawString("Rect Area: " + rect.getArea(), 50, 35); 
		drawArea(g, rect, 50, 35); 
		//g.drawString("Rect Perimeter: " + rect.getPerimeter(), 50, 55);
		drawPerimeter(g, rect, 50, 55);  
		//g.drawString("Triangle Area: " + tri.getArea(), 50, 75);
		drawArea(g, tri, 50, 75); 
		//g.drawString("Triangle Perimeter: " + tri.getPerimeter(), 50, 95);
		drawPerimeter(g, tri, 50, 95); 
		g.drawOval(cir.x - (int)cir.k/2, cir.y - (int)cir.k/2, cir.k, cir.k); 
		//g.drawString("Circle Area: " + cir.getArea(),  50, 115);
		drawArea(g, cir, 50, 115);   
		//g.drawString("Circle Perimeter: " + cir.getPerimeter(), 50, 135);
		drawPerimeter(g, cir, 50, 135);  
	}
}