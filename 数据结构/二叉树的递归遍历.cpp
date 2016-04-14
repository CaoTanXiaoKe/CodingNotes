/**
* ��Ŀ�� UVa 548 
* ���������� ����һ����Ȩ��Ȩֵ��������ͬ���Ҷ���С��10000�����������Ķ�����������ͺ�������� 
* ��һ��Ҷ��ʹ����������·���ϵ�Ȩֵ����С�� ����ж�⣬ ʹ��Ҷ�ӽ�㱾���ȨֵӦ����С��  
* ���룺 ��һ��Ϊ��������� �ڶ���Ϊ�������� 
* ʾ���� 
* 	���룺 	7 8 11 3 5 16 12 18 
*           8 3 11 7 16 18 12 5 
* 	����� 3
* 
* ��������� �ݹ齨�� ---- > DFS��������   
*/

#include <iostream>
#include <string>
#include <sstream>
#include <algorithm>
using namespace std; 

//��Ϊ�����ڵ��Ȩֵ������ͬ�Ҷ����������� ֱ����Ȩֵ��Ϊ�����  
const int maxv = 10000 + 10; 
int in_order[maxv], post_order[maxv], lch[maxv], rch[maxv]; 
int n; 

bool read_list(int * a)
{
	string line; 
	if(!getline(cin, line)) return false; 
	stringstream ss(line); // ����һ�������õ���Ŷ��  
	n = 0; 
	int x; 
	while(ss >> x) a[n++] = x; 
	return n > 0; 
}

// ��in_order[L1..R1] �� post_order[L2..R2]����һ�ö������� �������� 
int build(int L1, int R1, int L2, int R2)
{
	if (L1 > R1) return 0; 	// ���� 
	int root = post_order[R2]; 
	int p = L1; 
	while(in_order[p] != root) p++; 
	int cnt = p - L1; 		// �������Ľ�����
	lch[root] = build(L1, p-1, L2, L2+cnt-1);
	rch[root] = build(p+1, R1, L2+cnt, R2-1); 
	return root;  
} 

int best, best_sum; 	//ĿǰΪֹ�����Ž�Ͷ�Ӧ��Ȩ�� 

void dfs(int u, int sum)
{
	sum += u; 
	if(!lch[u]&&!rch[u]) // Ҷ�� 
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
		best_sum = 1000000000; // �ӽ� int ���͵����ֵ��ʮλ���� 
		dfs(post_order[n-1], 0); 
		cout << best << "\n";  
	}
	return 0; 
}
