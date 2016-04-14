/**
* 题目： UVa 548 
* 问题描述： 给定一个带权（权值各不不相同，且都是小于10000的正整数）的二叉树的中序和后序遍历， 
* 找一个叶子使得它到树根路径上的权值和最小， 如果有多解， 使该叶子结点本身的权值应尽量小。  
* 输入： 第一行为中序遍历， 第二行为后续遍历 
* 示例： 
* 	输入： 	7 8 11 3 5 16 12 18 
*           8 3 11 7 16 18 12 5 
* 	输出： 3
* 
* 解决方案： 递归建树 ---- > DFS遍历方案   
*/

#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
using namespace std; 

//因为各个节点的权值各不相同且都是正整数， 直接用权值作为结点编号  
const int maxv = 10000 + 10; 
int in_order[maxv], post_order[maxv], lch[maxv], rch[maxv]; 
int n; 

bool read_list(int * a)
{
	string line; 
	if(!getline(cin, line)) return false; 
	stringstream ss(line); // 这是一个很有用的类哦。  
	n = 0; 
	int x; 
	while(ss >> x) a[n++] = x; 
	return n > 0; 
}

// 把in_order[L1..R1] 和 post_order[L2..R2]建成一棵二叉树， 返回树根 
int build(int L1, int R1, int L2, int R2)
{
	if (L1 > R1) return 0; 	// 空树 
	int root = post_order[R2]; 
	int p = L1; 
	while(in_order[p] != root) p++; 
	int cnt = p - L1; 		// 左子树的结点个数
	lch[root] = build(L1, p-1, L2, L2+cnt-1);
	rch[root] = build(p+1, R1, L2+cnt, R2-1); 
	return root;  
} 

int best, best_sum; 	//目前为止的最优解和对应的权和 

void dfs(int u, int sum)
{
	sum += u; 
	if(!lch[u]&&!rch[u]) // 叶子 
	{
		if (sum < best_sum || (sum == best_sum && u < best)) 
		{
			best = u; 
			best_sum = sum; 
		}
	}
	if(lch[u]) dfs(lch[u], sum); 
	if(rch[u]) dfs(rch[u], sum); 
} 

int main()
{
	while(read_list(in_order))
	{
		read_list(post_order); 
		build(0, n-1, 0, n-1); 
		best_sum = 1000000000; // 接近 int 类型的最大值（十位数） 
		dfs(post_order[n-1], 0); 
		cout << best << "\n";  
	}
	return 0; 
}
