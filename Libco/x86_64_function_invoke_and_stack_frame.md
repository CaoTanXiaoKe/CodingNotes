![img](https://pic2.zhimg.com/80/v2-bd5a0aa1625c4445ba33e506b91dba29_hd.png)



**函数调用**:

在函数调用时，执行的操作为: 父函数将调用参数从后向前压栈，将返回地址压栈保存，跳转到子函数起始地址执行，子函数将父函数栈帧起始地址(%rbp)压栈，

```assembly
...   		 	# 参数压栈, 调用参数从右向左压栈。
call FUNC  	# 将返回地址压栈，并跳转到子函数 FUNC 处执行
...  			 	# 函数调用的返回位置

FUNC:  			# 子函数入口
pushq %rbp  # 保存旧的帧指针，相当于创建新的栈帧
movq  %rsp, %rbp  # 让 %rbp 指向新栈帧的起始位置
subq  $N, %rsp  	# 在新栈帧中预留一些空位，供子程序使用，用 (%rsp+K) 或 (%rbp-K) 的形式引用空位
```

`call` 指令做了两件事情，将返回地址(程序计数器，PC)压入栈中以及跳转到子函数执行。



**函数返回**:

函数返回时，一般是把结果存放到%rax寄存器进行返回；然后把栈的结构恢复到函数调用之前的状态，调用父函数的返回地址继续执行；因为我们已经在函数调用时，保存了返回地址和父函数的栈帧起始地址。

```assembly
movq %rbp, %rsp 	# 使 %rsp 和 %rbp 指向同一位置，即子栈帧的起始处
popq %rbp 	# 将栈中保存的父栈帧的 %rbp 的值赋值给 %rbp，并且 %rsp 上移一个位置指向父栈帧的结尾处
```

`leave` 指令用于恢复父函数的栈帧，即相当于上面的两个指令。ret 用于跳转到返回地址处。父函数栈帧恢复后，如下: 

![img](https://pic4.zhimg.com/80/v2-039d97b92f66e84801938c0e4b63e7cf_hd.png)



#### 示例

```bash
gcc -g -c test.c
objdump -d -M intel -S test.o
```





### 挂起协程和恢复执行

#### 挂起协程

1. 程序中主动调用co_yield_ct()
2. 程序中调用了poll() 或 co_cond_timedwait(), 陷入等待。
3. 程序中调用了connect(), read(), write(), recv(), send()等被hook的系统调用。

#### 恢复协程执行

1. 对应上面第一种，程序中主动yield时，目的协程同样依赖于当前线程调用co_resume(), "唤起"目的协程。
2. poll()的文件描述符事件就绪或者超时； co_cond_timedwait()就绪(等到了相应的co_cond_signal())或者超时。
3. connect(), read(), write()等接口，相应的文件描述符就绪或者接口超时。