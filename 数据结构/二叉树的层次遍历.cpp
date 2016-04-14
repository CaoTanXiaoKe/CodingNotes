// ��Ŀ�� UVA 122  
/*
���������� ����һ������������ʾ������ ������������һ����������ı�־ 
Ҫ������� �����ϵ��£� �����ҵ�˳��������ֵ�� ������α����� 
��������� ���� ----> bfs���� 
ʾ����
	 ���룺(3��L) (4,R) (5,) () 
	 ����� 5 3 4
	 
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

// �������ṹ�� 
struct Node{
	bool have_value; 			// �Ƿ񱻸�ֵ�� 
	int v; 						// �ڵ�ֵ 
	Node *left, *right; 
	Node():have_value(false), left(NULL), right(NULL) {
	} 				// ���캯��  
};

char s[maxn]; // ��������� 
int failed; 
Node* root; 

Node* newnode(); // �����½�� 
void remove_tree(Node* u); // �ͷ��� 
void addnode(int v, char * s); // ��ӽ�� 
bool read_input();				// ���� 
bool bfs(vector<int>& ans); 	// BFS��α��� 


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
	root = newnode();		 // ���������
	for (;;)
	{
		if (scanf("%s", s) != 1) return false; // �����������
		if (!strcmp(s, "()")) break;			// ����������־�� �˳�ѭ��
		int v; 
		sscanf(&s[1], "%d", &v); 				// ������ֵ
		addnode(v, strchr(s, ',') + 1); 		// ���Ҷ��ţ� Ȼ������� 
	}	
	return true; 
}

void addnode(int v, char *s)
{
	int n = strlen(s); 
	Node* u = root; 					// �Ӹ���㿪ʼ������ 
	for (int i = 0; i < n; i++)
		if(s[i]=='L')
		{
			if(u->left==NULL)        	// ��㲻���� 
				u->left = newnode(); 	// �����½�� 
			u = u->left; 				// ������ 
		}
		else if(s[i]=='R')
		{
			if (u->right == NULL)
				u->right = newnode(); 
			u = u->right; 
		}								// ������������� ������Ǹ������� 
		if(u->have_value) failed = true; // �Ѿ�����ֵ�� ������������ 
		u->v = v; 
		u->have_value = true; 				// С�ģ� ����������� 
}

bool bfs(vector<int>& ans)
{
	queue<Node*> q;						// ��ʼʱֻ��һ������� 
	ans.clear(); 
	q.push(root); 
	while(!q.empty())
	{
		Node* u = q.front(); q.pop(); 
		if(!u->have_value) return false; // �н��û�б���ֵ���� ������������  
		ans.push_back(u->v); 				// �����������β�� 
		if(u->left != NULL) q.push(u->left); 	// �����ӽ�㣨����У��Ž����� 
		if(u->right != NULL) q.push(u->right); 	// �����ӽ�㣨����У��Ž����� 
	}
	return true; 					// ������ȷ 
}
