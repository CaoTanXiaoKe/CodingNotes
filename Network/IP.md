### IP 中的几个重要概念: `Netmask` , `Network`, `Broadcast`, 可用的IP数目。

- `Network` 是网络号。
- `Netmask` 是子网掩码。
- `Broadcast` 是该网段内的广播地址。

举例： 计算 172.16.0.0/23 的 `Network`, `Netmask`, `Broadcast` 以及可用IP数目。

- `Network`是由 172.16.0.0 的前 23位确定的，即为：172.16.0.0
- `Netmask`为 23位的1， 即： 11111111.11111111.11111110.00000000 为： 255.255.254.0
- `Broadcast`即在该网段内，主机号最大的那个ip（这个网段内主机号有 32-23=9位）。即为： 172.16.1.255。
- 可用的IP数量： 不算广播IP， 不算网络IP，即： 2^9 - 2 = 510

另外： 
**在同物理网段的主机如果设定相同的网域IP范围(不可重复)，则这些主机都可以透过CSMA/CD的功能直接在区网内用广播进行网路的连线，亦即可以直接网卡对网卡传递资料(透过MAC讯框)**







### 资料

- [Guide to IP Layer Network Administration with Linux](http://linux-ip.net/html/index.html)
- [ip route](http://linux-ip.net/html/tools-ip-route.html)
- [ip rule](http://linux-ip.net/html/tools-ip-rule.html)
- [Linux 虚拟网络设备之veth](https://segmentfault.com/a/1190000009251098)
- [Linux网络 - 数据包的接收过程](https://segmentfault.com/a/1190000008836467)
- [Linux虚拟网络设备之bridge(桥)](https://segmentfault.com/a/1190000008836467)
- [Linux Namespace 和 Cgroup](https://segmentfault.com/a/1190000009732550)

