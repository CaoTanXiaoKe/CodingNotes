// 尾插法建立单链表 LinkList

template <DataType>::LinkList(DataType a[], int n)
{
	first = new Node;					// 生成头结点 
	r = first;							// 尾指针初始化 
	for (int i = 0; i < n; i++)
	{
		s = new Node; s->data = a[i]; 
		r->next = s; 
		r = s; 
	}
	r->next = NULL; 
}
