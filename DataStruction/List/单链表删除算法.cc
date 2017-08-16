// 单链表删除算法 Delete

template <DataType>::Delete(int i)
{
	p = first; count = 0; 
	while (p != NULL && count < i-1)
	{
		p = p->next; 
		count++; 
	}
	if (p == NULL || p->next == NULL)
		throw "位置";
	else {
		q = p->next; x = q->data; // 暂存被删结点
		p->next = q->next;			// 摘链  
		delete q; 
		return x; 
	}
}
