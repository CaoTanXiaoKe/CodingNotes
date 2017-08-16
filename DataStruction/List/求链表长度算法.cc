// 求链表长度算法

template <class DataType>
int LinkList<DataType>::length()
{
	p = first->next; count=0; 
	while(p != Null)
	{
		p = p->next; 
		count++; 
	}
	return count; 
}
