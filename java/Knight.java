
public class Knight {
	final static  int SIZE = 8;
	private static int total = 0; 
	public static void Display(int [] trace)
	{
		for(int i = 0; i < SIZE; i++)
		{
			for(int j = 0; j < SIZE; j++)
			{
				System.out.printf("%3d", trace[i*SIZE+j]); // 这里需要控制格式
			}
			System.out.println(""); 
		}
		System.out.println(""); 
		System.out.println(""); 
	}
	public  static void Search(int step, int x, int y, int[] trace)
	{
		int posX, posY; 
		int dx[ ] = {-1,-2,-2,-1, 1, 2, 2, 1};
		int dy[ ] = { 2, 1,-1,-2,-2,-1, 1, 2};
		for(int i = 0; i < 8; i++)
		{
			posX = x + dx[i]; 
			posY = y + dy[i]; 
			if(posX>=0&&posX<SIZE&&posY>=0&&posY<SIZE&&(trace[posX*SIZE+posY]==0))
			{
				trace[posX*SIZE+posY] = step; 
				if(step < SIZE*SIZE)
				{
					Search(step+1, posX, posY, trace); 
				}
				else
				{
					Display(trace); 
					total++; 
				}
				trace[posX*SIZE+posY] = 0; 
			}
		}
	}
	public static void main(String[] args)
	{
		long start =  System.currentTimeMillis(); 
		// 时间函数
		int trace[] = new int[64];
		trace[0] = 1; // 初始化trace[0*SIZE+0] = 1; 
		Search(2, 0, 0, trace); 
		long finish = System.currentTimeMillis(); 
		System.out.println("Total ways: " + total); 
		System.out.println("Total times used:  " + (finish - start) + "ms"); //输出所用时间
	}
}
