// 头插法建立单链表 LinkList

template <DataType>::LinkList(DataType a[], int n)
{
	first = new Node; first->next = NULL; 
	for(int i = 0; i < n; i++)
	{
		s = new Node; s->data = a[i]; 
		s->next = fist->next; fist->next = s; 
	}
}

