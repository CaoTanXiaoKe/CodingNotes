// 单链表安值查找算法 Locate

template <class DataType>

int LinkList<DataType>::Locate(DataType x)
{
	p = first->next; count = 1;			// 工作指针 p 和累加器 count 初始化
	while (p != NULL)
	{
		if (p->data == x) return count;
		p = p->next; 
		count++; 
	}
	return 0; 
}


