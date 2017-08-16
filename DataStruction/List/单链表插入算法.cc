// 单链表插入算法 Insert

template <class DataType>

void LinkList<DataType>::Insert(int i, DataType x)
{	
	p = first; count = 0; 
	while (p != NULL && count < i-1)
	{
		p = p->next; 
		count++; 
	}
	
	if (p == NULL) throw "位置";
	else {
		s = new Node; s->data = x; 
		s->next = p->next; 
		p->next = s; 
	}
}

