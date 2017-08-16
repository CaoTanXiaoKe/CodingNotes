## tcpdump 的简单用法

一般情况下，非HTTP协议的网络分析，在服务器端用tcpdump比较多，在客户端用wireshark比较多，两个抓包软件的语法是一样的。

### 一、基本语法

#### 1.1、过滤主机

- 抓取所有经过eth0，目的或源地址是192.168.1.1的网络数据
`tcpdump -i eth0 host 192.168.1.1`

- 指定源地址
`tcpdump -i eth0 src host 192.168.1.1`

- 指定目的地址
`tcpdump -i eth0 dst host 192.168.1.1`

#### 1.2、过滤端口

- 抓取所有经过eth0，目的或源端口是25的网络数据
`tcpdump -i eth0 port 25`

- 指定源端口
`tcpdump -i eth0 src port 25`

- 指定目的端口
`tcpdump -i eth0 dst port 25`

#### 1.3、网络过滤

- `tcpdump -i eth0 net 192.168`
- `tcpdump -i eth0 src net 192.168`
- `tcpdump -i eth0 dst net 192.168`

#### 1.4、协议过滤

- `tcpdump -i eth0 arp`
- `tcpdump -i eth0 ip`
- `tcpdump -i eth0 tcp`
- `tcpdump -i eth0 udp`
- `tcpdump -i eth0 icmp`

#### 1.5、常用表达式

- 非 : ! or "not" (去掉双引号)  
- 且 : && or "and"  
- 或 : || or "or"
- 抓取所有经过eth0，目的地址是192.168.1.254或192.168.1.200端口是80的TCP数据
`tcpdump -i eth0 '((tcp) and (port 80) and ((dst host 192.168.1.254) or (dst host 192.168.1.200)))'`

- 抓取所有经过eth0，目标MAC地址是00:01:02:03:04:05的ICMP数据
`tcpdump -i eth0 '((icmp) and ((ether dst host 00:01:02:03:04:05)))'`

- 抓取所有经过eth0，目的网络是192.168，但目的主机不是192.168.1.200的TCP数据
`tcpdump -i eth0 '((tcp) and ((dst net 192.168) and (not dst host 192.168.1.200)))'`

### 二、高级包头过滤
略， 见参考资料。 

## wireshark 简单应用

#### 1.安装wireshark

终端运行：`sudo apt-get install wireshark`

#### 2.修改init.lua

直接运行wireshark的话会报错：

> Lua: Error during loading:
[string "/usr/share/wireshark/init.lua"]:45: dofile has been disabled

要对其进行修改，终端运行

`sudo gedit /usr/share/wireshark/init.lua`

倒数第二行改为: `--dofile(DATA_DIR.."console.lua")`

#### 3.启动软件

终端运行：`sudo wireshark`

使用教程， 见参考资料。 





#### 参考资料
- [tcpdump使用技巧](http://linuxwiki.github.io/NetTools/tcpdump.html)
- [wireshark抓包工具使用教程](http://fangxin.blog.51cto.com/1125131/735178)