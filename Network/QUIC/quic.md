## QUIC协议

**QUIC**(**Q**uick **U**DP **I**nternet **C**onnections)协议是google开发的一种基于UDP的新的互联网通信协议，解决了大量目前常用的传输层和应用协议所存在的问题。而且和目前常用的协议具有较好的兼容性，转换协议对应用程序改动不大。QUIC协议提供的功能类似于 TCP+TLS+HTTP2。



相对于 TCP + TLS + HTTP2, QUIC协议拥有以下优势：

- 建立连接时延迟比较小，在请求比较频繁时，通过缓存密钥等信息，可以实现在发送payload数据前，不需要进行握手。
- 具有更好的拥塞控制。能够识别和控制重传frame， 具有更强大的选择确认功能。
- 可以实现没有行头阻塞的多路复用。HTTP2通过控制stream的发送速度也能一定程度的避免行头阻塞，但是在行头数据需要重传，对端无法及时读取数据的情况下，仍然后阻塞后面的frame。QUIC协议的stream更加独立，行头阻塞只影响这个特定的stream。
- 错误恢复。针对特地的场景，QUIC协议可以开启故障恢复功能，类似于RAID-4, 通过增加冗余，可以恢复需要重传的数据。
- 连接迁移。QUIC使用一个client端生成的64位的ID唯一标识连接，相对的，TCP使用<IP:PORT  IP:PORT>四元组，因此相对于TCP，在QUIC协议下，client端可以改变IP而保持连接不断。



### 资料

- [What is QUIC?](https://docs.google.com/document/d/1gY9-YNDNAB1eip-RTPbqphgySwSNSDHLq9D5Bty4FSU/edit)