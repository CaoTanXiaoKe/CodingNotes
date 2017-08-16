// 无参构造函数 LinkList

template <class DataType>

LinkList<DataType>::LinkList()
{	
	first = new Node; 
	first->next = NULL; 
}
