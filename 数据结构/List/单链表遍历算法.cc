// 单链表遍历算法: PrintList
void LinkList<DataType>::PrintList()
{
	p = first->next;		// 工作指针初始化
	while(p != NULL) 
	{
		cout << p->data; 
		p = p->next;		// 工作指针后移, 注意不能写成 p++ 
	}
}
