# Linux Command
需要掌握的Linux命令。 

### GCC 常用选项

- `gcc -E`： 预处理
- `gcc -S`: 编译
- `gcc -c`: 汇编
- `gcc ` : 链接/(全过程)
- `gcc -g`: 向生成的ELF文件中添加调试信息
- `gcc -D` : 定义宏
- `gcc -U`： 取消宏
- `gcc -I`: 指定搜索头文件的路径
- `gcc -llibrary`: 连接时搜索由 library 命名的库。
- `gcc -L` : 把指定的目录 dir 加到连接程序搜索库文件的路径表中
- `gcc -static` : 指定使用静态连接
- `gcc -O` : 指定优化级别， 默认为 O2 
- `gcc -shared -fPIC -o `: 创建共享库
- `gcc -fno-common` : 告诉链接器，在遇到多重定义的全局符号时，输出一条警告信息。
- `gcc -Wall` : 开启警告


### gdb 调试

#### 调试已运行的程序：
1. 在 Linux 下用 `ps` 或 `pgrep <program>` 查看正在运行的程序的PID(进程ID), 然后用 `gdb <program> PID`格式，挂接正在运行的程序。 
2. 先用 `gdb <program>` 关联上源代码，并运行 gdb, 在 gdb 中用 `attach`命令来挂接进程 PID, 并用 `detach`来取消挂接的进程。

#### 使程序暂停的方式：
1. 设置和显示断点
```
break _linenum_
break _linenum_ if _condition_
break _function_
break _file:linenum_
break _file:function_
break _*address_
break
info break [n]
info breakpoints [n]
```

2. 设置和显示观察点
```
watch _expr_
rwatch _expr_
awatch _expr_
info breakpoints
info watchpoints
```

3. 设置捕捉点
```
catch _event_
event: 
	signal;		signal _signum_;		throw;		catch;
				start;		exit;		load;		unload; 	
```

#### 调试线程：

```
break <linespec> thread <threadno>
break <linespec> thread <threadno> if _condition_
info threads
```

#### 查看栈信息
```
backtrace
bt
bt <n>
up
down
info frame
frame
```

#### 其他查看命令
```
info line
disassembly func
print/f <expr>
print/x <expr>
print/t <expr>
print/d <expr>
display/i $pc
```

### makefile 编写

make 工作时的执行步骤：
1. 读入所有的Makefile
2. 读入被 include 的其它 Makefile
3. 初始化文件中的变量
4. 推导隐晦规则，并分析所有规则。 
5. 为所有的目标文件创建依赖关系链。
6. 根据依赖关系链，决定哪些目标要重新生成。
7. 执行生成命令。

### netstat 命令
1. 列出所有端口：（包括监听的和未监听的）
`netstat -a`
2. 列出所有 tcp 端口：
`netstat -at`
3. 列出所有有监听的服务状态：
`netstat -l`
4. 使用 netstat 工具查询端口：
`netstat -antp | grep 8888` 查看8888端口正在被哪个进程（程序）在占用。


### 网络抓包 tcpdump
1. 指定网卡： `tcpdump -i eth0`
2. 指定目的地址或源地址：`tcpdump -i eth0 host 192.168.1.1`
3. 指定源地址：`tcpdump -i eth0 src host 192.168.1.1`
4. 指定目的地址：`tcpdump -i etch0 dst host 192.168.1.1`
5. 过滤端口：`tcpdump -i eth0 port 22`
6. 指定源端口：`tcpdump -i eth0 src port 22`
7. 指定目的端口：`tcpdump -i eth0 dst port 22`
8. 网络过滤：
```
tcpdump -i eth0 net 192.168
tcpdump -i eth0 src net 192.168
tcpdump -i eth0 dst net 192.168
```

9. 协议过滤：
```
tcpdump -i eth0 arp
tcpdump -i eth0 ip
tcpdump -i eth0 tcp
tcpdump -i eth0 udp
tcpdump -i eth0 icmp
```

- 常用表达式：
```
! 	或	not 
&& 	或	and
||	或 	or
```

- [tcpdump使用技巧](http://linuxwiki.github.io/NetTools/tcpdump.html)

### ipcs 与 ipcrm 命令
1. 查看系统使用的IPC资源： `ipcs`
2. 分别查看各种类型的 IPC 资源：
	- `ipcs -m`: 查看系统使用的
	- `ipcs -q`:
	- `ipcs -s`:
3. 查看系统 IPC 参数：
`ipcs -l`

4. 修改 IPC 系统参数：
	- 修改: `/etc/sysctl.conf`文件（root模式下），保存后使用 `sysctl -p`生效。 

5. 清除 IPC资源： ipcrm
	- `ipcrm -M shmkey`:	移除用 shmkey 创建的共享内存段。
	- `ipcrm -m shmid`:		移除用 shmid 标识的共享内存段。
	- `ipcrm -Q msgkey`:	移除用 msgkey 创建的消息队列。 
	- `ipcrm -q msgid`:		移除用 msgid 标识的消息队列。
	- `ipcrm -S semkey`:	移除用 semkey 创建的信号。
	- `ipcrm -s semid`:		移除用 semid 标识的信号。 

- [ipcs 查询进程间通信状态](http://linuxtools-rst.readthedocs.io/zh_CN/latest/tool/ipcs.html)

