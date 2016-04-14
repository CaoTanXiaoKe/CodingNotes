// 题目： UVA 122  
/*
问题描述： 输入一个二叉树（如示例）。 （）空括号是一组输入结束的标志 
要求输出： 按从上到下， 从左到右的顺序，输出结点值。 即：层次遍历。 
解决方案： 建树 ----> bfs遍历 
示例：
	 输入：(3，L) (4,R) (5,) () 
	 输出： 5 3 4
	 
	               5 
	             /   \
				3     4  
*/ 

#include <cstdio>
#include <cstdlib>
#include <queue>
#include <vector>
#include <cstring>
#include <fstream>
using namespace std; 

const int maxn = 1000 + 10; 

// 二叉树结构体 
struct Node{
	bool have_value; 			// 是否被赋值过 
	int v; 						// 节点值 
	Node *left, *right; 
	Node():have_value(false), left(NULL), right(NULL) {
	} 				// 构造函数  
};

char s[maxn]; // 保存读入结点 
int failed; 
Node* root; 

Node* newnode(); // 申请新结点 
void remove_tree(Node* u); // 释放树 
void addnode(int v, char * s); // 添加结点 
bool read_input();				// 输入 
bool bfs(vector<int>& ans); 	// BFS层次遍历 


int main()
{
	vector<int> ans; 
	vector<int>::iterator it;
	while(read_input()) {
    if(!bfs(ans)) failed = 1;
    if(failed) printf("not complete\n");
    else {
      for(it = ans.begin(); it != ans.end(); it++)
	  {
        if(it != ans.begin()) printf(" ");
        printf("%d", *it);
      }
      printf("\n");
    }
  }
	return 0;  
} 

Node* newnode() { return new Node(); }
void remove_tree(Node* u)
{
	if( u == NULL) return; 
	remove_tree(u->left); 
	remove_tree(u->right); 
	delete u; 
}

bool read_input(){
	failed = false; 
	root = newnode();		 // 创建根结点
	for (;;)
	{
		if (scanf("%s", s) != 1) return false; // 整个输入结束
		if (!strcmp(s, "()")) break;			// 读到结束标志， 退出循环
		int v; 
		sscanf(&s[1], "%d", &v); 				// 读入结点值
		addnode(v, strchr(s, ',') + 1); 		// 查找逗号， 然后插入结点 
	}	
	return true; 
}

void addnode(int v, char *s)
{
	int n = strlen(s); 
	Node* u = root; 					// 从根结点开始往下走 
	for (int i = 0; i < n; i++)
		if(s[i]=='L')
		{
			if(u->left==NULL)        	// 结点不存在 
				u->left = newnode(); 	// 建立新结点 
			u = u->left; 				// 往左走 
		}
		else if(s[i]=='R')
		{
			if (u->right == NULL)
				u->right = newnode(); 
			u = u->right; 
		}								// 忽略其他情况， 即最后那个右括号 
		if(u->have_value) failed = true; // 已经赋过值， 表明输入有误 
		u->v = v; 
		u->have_value = true; 				// 小心， 别忘记做标记 
}

bool bfs(vector<int>& ans)
{
	queue<Node*> q;						// 初始时只有一个根结点 
	ans.clear(); 
	q.push(root); 
	while(!q.empty())
	{
		Node* u = q.front(); q.pop(); 
		if(!u->have_value) return false; // 有结点没有被赋值过， 表明输入有误。  
		ans.push_back(u->v); 				// 增添到输出队列尾部 
		if(u->left != NULL) q.push(u->left); 	// 把左子结点（如果有）放进队列 
		if(u->right != NULL) q.push(u->right); 	// 把右子结点（如果有）放进队列 
	}
	return true; 					// 输入正确 
}
