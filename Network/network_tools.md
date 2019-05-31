#### `route`命令
- `route` 查看路由表: `route` 或 `route -n` 

```
// route
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
default         10.135.0.1      0.0.0.0         UG    0      0        0 eth0
10.135.0.0      *               255.255.192.0   U     0      0        0 eth0
172.17.0.0      *               255.255.0.0     U     0      0        0 docker0
```

```
// route -n
Kernel IP routing table
Destination     Gateway         Genmask         Flags Metric Ref    Use Iface
0.0.0.0         10.135.0.1      0.0.0.0         UG    0      0        0 eth0
10.135.0.0      0.0.0.0         255.255.192.0   U     0      0        0 eth0
172.17.0.0      0.0.0.0         255.255.0.0     U     0      0        0 docker0
```

```
// ifconfig
docker0   Link encap:Ethernet  HWaddr 02:42:f3:f2:cd:fe  
          inet addr:172.17.0.1  Bcast:172.17.255.255  Mask:255.255.0.0
          UP BROADCAST MULTICAST  MTU:1500  Metric:1
          RX packets:6032 errors:0 dropped:0 overruns:0 frame:0
          TX packets:7116 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:0 
          RX bytes:342199 (342.1 KB)  TX bytes:16217881 (16.2 MB)

eth0      Link encap:Ethernet  HWaddr 52:54:00:6e:ff:7e  
          inet addr:10.135.51.111  Bcast:10.135.63.255  Mask:255.255.192.0
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:4713904 errors:0 dropped:0 overruns:0 frame:0
          TX packets:5205725 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:590115189 (590.1 MB)  TX bytes:3592782830 (3.5 GB)

lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:6 errors:0 dropped:0 overruns:0 frame:0
          TX packets:6 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1 
          RX bytes:300 (300.0 B)  TX bytes:300 (300.0 B)
```
    - Destination: 目的IP所在的 Network。
    - Gateway: 即该网关IP，若为 0.0.0.0 表示不需要通过网关
    - Genmask: 即 Netmask 子网掩码。
    - Flags: 共有多少个旗标可以来表示该网域或主机代表的意义：
        - U: 代表该路由可用；
        - G: 代表该网域需要经由 Gateway 来帮忙传递；
        - H: 代表该行路由为一部主机，而不是一整个网域；
        - Iface： 就是Interface(介面)的意思。

由上面的信息可以知道，这台机器所在的网域(网段)为  10.135.0.0/ 255.255.192.0 即： 10.135.0.0/18。所以要与网络号为： 10.135.0.0的IP通信时，不需要经过网关，自己可以通过网段多播进行通信。 如果要与网络号为 172.17.0.0的IP通信，通过 docker0虚拟网卡，也不需要经过网关。与除此之外的其他网域进行通信时，需要经过网关 10.135.0.1 转发。


#### 一些命令
- ifconfig
```
// ifconfig

```

    - eth0：就是网路卡的代号；
    - HWaddr：网卡硬件地址，即MAC；
    - inet addr：IPv4的IP位址，后面是Bcast；
    - Mask分别代表的是Broadcast与netmask！
    - inet6 addr：IPv6的版本的IP；
    - MTU：MTU 最大分组长度；
    - RX：网路由启动到目前为止的封包接收情况； packets代表封包数、errors代表封包发生错误的数量、   dropped代表封包由于有问题而遭丢弃的数量等等
    - TX：与RX相反，为网路由启动到目前为止的传送情况；
    - collisions：代表封包碰撞的情况，如果发生太多次，表示你的网路状况不太好；
    - txqueuelen：代表用来传输资料的缓冲区的储存长度；
    - RX bytes, TX bytes：总接收、传送的位元组总量


### netlink
- 监视网络事件
`ip monitor route`
- 添加路由选择条目
`ip route add 192.168.2.11 via 192.168.2.20`
- 删除路由选择条目
`ip route del 192.168.2.11`


- 加入RTNLGRP_LINK组播组监视链路事件。
`ip monitor link`
- 添加一个VLAN接口
`vconfig add eth0 200`
- 删除一个VLAN接口
`vconfig rem eth0.200`
- 安装网桥管理工具
`yum install bridge-utils`
- 添加一个网桥
`brctl addbr mybr`
- 删除一个网桥
`brctl rmbr mybr`



###

- RST出现的场景
- 

- `close()`: 执行close()系统调用关闭TCP连接，内核会首先检查TCP的read buffer里有没有客户端发送过来的数据留在内核态没有被用户态进程读取，如果有则发送给客户端RST报文来关闭TCP连接并丢弃write buffer里的数据，如果没有则等待 write buffer里的数据发送完毕，然后再经过正常的四次挥手报文断开连接。 






#### 网络抓包 tcpdump

选项与参数
```
- A: 封装的内容以ASCII显示，通常用来抓取 www的网页资料。
- e：以MAC封包的格式来显示。
- nn: 使用 IP 和 Port 显示，而不是主机名和服务名。
- q: 仅列出简短的封包资料，比较精简。
- X: 使用十六进制(hex)以及ASCII的显示封包内容，用于监听封包内容。
- i: 指定要监听网卡界面
- w：指定要把内容写入的文件
- r: 指定要读取的文件（文件内容是用tcpdump -w 写入的）
- c: 指定要监听的封包数
```


举例： 

- `tcpdump -i eth0 -nn`: 监听 eth0 网卡上的数据包
- `tcpdump -i eth0 -nn port 21`: 监听 eth0 网卡，端口为21（来源端口以目的端口）上的数据包。
- `tcpdump -i eth0 port 21 -nn -X `: 监听 eth0 网卡，21号端口，并且以hex和ASCII的形式显示数据包内容。
- `tcpdump -i eth0 -nn port 22 and src host 123.207.54.56`: 监听 eth0 网卡， 端口为 22 来源ip为 123.207.54.56的数据包。



#### netstat
- `netstat -tlunp` : 显示 tcp，listen状态，udp, 数字化显示ip和port， 显示进程信息。

#### nmap
**`nmap [扫描类型] [扫描参数] [host地址与范围]`**
- `sT`: 扫描已经建立的TCP连接。
- `sS`: 扫描 TCP 带有 SYN的报文。
- `sP`: 以 ping 的方式进行扫描。
- `sU`: 扫描 UDP 报文。
- `sO`: 以IP协议(protocol)对主机进行扫描
- `PT`: 使用TCP里的ping 进行扫描，可以扫描哪些主机是存活的。
- `PI`: 使用实际的ping(带有ICMP报文信息)进行扫描。
- `p`: port range, 例如： 1024-, 80-1023, 3000-6000

举例：
- 扫描本机的 TCP/UDP 端口： `nmap -sTU localhost`
- 透过ICMP报文的检测，分析网段内哪些主机是启动的。 `nmap -sP 123.207.54.56/18`
- 网段内各个主机的port都探测一下。 `nmap 123.207.54.56/18`

#### nc
- `nc [-u][IP|host][port]`
    - `nc localhost 25`: 连接本地端 port 25
- `nc -l [IP|host][port]`
- `nc -l localhost 20000 &` 监听本地端口 20000
- 



---