/*************************************************************
* Title: 约瑟夫环--数组实现
* author：陈闻恪
* Description: 用数组标记的方式，实现约瑟夫出队顺序。
*************************************************************/
#include <iostream>
const int MAXN = 50; 
using namespace std;

int n, k, m, a[MAXN];

int Move(int p, int t);    // 输入开始位置 p， 固定循环长度t； 返回：出队人的位置 

int main()
{
	cout << "请分别输入总人数: n, 开始位置: k, 循环节: m." << endl;
	while((cin >> n >> k >> m))
	{
		if(k>n) 
		{
			cout << "输入有误， 请注意（k < n, k, m, n > 0）\n"; 
			continue; 
		}
		for(int i = 1; i <= n; i++) a[i] = n;
		int left = n;  // left 为队列中还剩余的人数。 
		int p = k-1 + n; 
		while(left)
		{
			p = Move(p, m); 
			cout << p << ", ";
			left--;
			a[p] = 0;    // 标记这个位置的人已经出队。 
		}		
		cout << endl << endl;
		cout << "请分别输入总人数: n, 开始位置: k, 循环节: m." << endl;
	}
	return 0; 
}

int Move (int p, int t)
{
	while(t--)
	{
		do { p = p % n + 1; } while(a[p] == 0); // 忽略已经出队的人
	}
	return p; 
}

