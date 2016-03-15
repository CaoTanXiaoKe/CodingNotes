/*************************************************************
* Title: 约瑟夫环--链表实现
* author：陈闻恪
* Description: 用链表逐步删除的方式，实现约瑟夫出队顺序。
*************************************************************/

#include <iostream>
#include <list>
using namespace std;

int main()
{
	int n, k, m; 
	cout << "请分别输入总人数: n, 开始位置: k, 循环节: m." << endl;
	while (cin >> n >> k >> m)
	{
		if(k>n||n<0||k<0||m<0) 
		{
			cout << "输入有误， 请注意（k < n, k, m, n > 0）\n"; 
			continue; 
		}
		list<int> Li(n);
		list<int>::iterator it = Li.begin(), pos = Li.begin(); // 用pos的指示走到的位置 
		// 初始化链表 
		for( int i = 1; it != Li.end(); ++i, ++it)
		*it = i;
		
		int t = (k-2+n) % n + 1;
		while(--t) // 初始化 pos 指示的位置即：从（k-1）开始数 
		{
			pos++; 
			if(pos == Li.end()) 
			pos = Li.begin();
		}
		while(n--)     // 队列中剩余的人数 n 
		{
			int t = m;
			while(t--)    // 走m步 
			{
				pos++;		 
				if(pos == Li.end()) 
				pos = Li.begin();  // 走 m 步后的pos指示的位置 
			} 
			cout << *pos << ", "; // 出队 
			it = pos; it--;  // 用 it 暂存 pos的位置，由于pos将被删除，
							 //所以下次开始数的位置为现在pos所指位置的前一个位置 
			Li.erase(pos); // 删除 出队的人。  
			pos = it; 
		}
		cout << endl << endl; 
		cout << "请分别输入总人数: n, 开始位置: k, 循环节: m." << endl;
		
	}
	return 0; 
}
