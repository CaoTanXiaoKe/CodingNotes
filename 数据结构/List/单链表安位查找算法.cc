// 单链表安位查找算法

template <class DataType>
DataType LinkList<DataType>::Get(int i)
{	
	p = first->next; count = 1; 
	while (p != NULL && count < i)
	{
		p = p->next; 
		count++; 
	}
	if (p == NULL) throw "位置";
	else return p->data; 
}
