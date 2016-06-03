// 析构函数 ~LinkList

template <DataType>::~LinkList()
{
	while (first != NULL) 
	{
		q = first;				// 暂存被释放节点
		first = first->next; 
		delete q; 
	}
}
