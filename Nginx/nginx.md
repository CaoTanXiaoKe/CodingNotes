### nginx概述

### nginx本身的配置

- `lingering_close`: lingering_close, 字面意思就是延迟关闭，也就是说，当Nginx要关闭连接时，并非立即关闭连接，而是先关闭TCP连接的写，再等待一段时间后再关掉连接的读。 
### nginx的特点
- 更快。一方面，单次请求会得到更快的响应；另一方面，在高峰期（例如有数以万计的请求）， Nginx可以比其他服务器更快的响应请求。 
- 高扩展性。模块之间耦合性极地，很方便开发专用模块和复用现有模块。 
- 高可靠性。核心框架设计的非常简单，优秀，各个work进程独立并master进程进行管理，常用模块非常稳定。 
- 低内存消耗。
- 单机支持10万以上的连接。理论上，Nginx支持的并发连接上限取决于内存，10万远未封顶。事实上，能否及时处理更多的并发请求是跟业务逻辑相关的。 
- 热部署。 master进程管理各个work工作进程的模式，使得Nginx可以在不停服的情况下更新可执行文件，更新配置项，更换日志文件等。
- 


- [源码安装](http://phpcodez.com/install-nginx-centos-from-source/)

### nginx的应用

##### 负载均衡+反向代理配置：
```
http {
    upstream backend {
        server backend1.example.com;
        server backend2.example.com; 
        server backend3.example.com; 
    }
    
    server {
        location / {
            proxy_pass http://backend;
        }
    }
}
```

```
http {
    upstream backend {
        server backend1.example.com;
        server 127.0.0.1:8080; 
        server unix:/tmp/backend3.sock; 
    }
    
    server {
        location / {
            proxy_pass http://backend;
        }
    }
}
```

```
http {
    upstream backend {
        ip_hash; 
        server backend1.example.com;
        server 127.0.0.1:8080 down; 
        server unix:/tmp/backend3.sock; 
    }
    
    server {
        location / {
            proxy_pass http://backend;
        }
    }
}
```
#### nginx常用命令
- 启动nginx 
    - `/usr/bin/nginx` 如果不正确，可以用`which nginx`命令查看。
    - `/usr/bin/nginx -t -c ~/mynginx.conf` 检查配置文件 **~/mynginx.conf**的语法是否正确。 
- 关闭nginx
    - `/usr/bin/nginx -s stop`
    - `kill -QUIT $(cat /usr/local/nginx/logs/nginx.pid)` 优雅的关闭。其中一般情况下 nginx把主进程写进 **/usr/local/nginx/logs/nginx.pid**, 当然也可以在编译的时候指定 `./configure`参数， 或者在配置文件里面更改这个路径。 
    - `kill -INT $(cat /usr/local/nginx/logs/nginx.pid)` 快速关闭。
    - `kill -TERM $(cat /usr/local/nginx/logs/nginx.pid)` 快速关闭。
    - `nginx -s stop` shut down quickly.
    - `nginx -s quit` shut down gracefully
- 重启nginx
    - `kill -HUP $(cat /usr/local/nginx/logs/nginx.pid)` 重新加载配置信息，用新配置生成新进程，优雅的关掉旧进程。
    - `nginx -s reload` reload configuration, start the new worker process with a new configuration, gracefully shut down old worker proceses. 
- 重新打开日志文件
    - `/usr/bin/nginx -s reopen` reopen log files. 
    - `kill -USR1 $(cat /usr/local/nginx/logs/nginx.pid)` reopen log files. 
    - `nginx -s reopen` reopen log files.

##### 参考资料：
- [Nginx CommandLine](https://www.nginx.com/resources/wiki/start/topics/tutorials/commandline/)



#### nginx与php-fpm

- [安装nginx, php-fpm, mysql](https://www.jianshu.com/p/79942f37b2dc)
- [安装nginx, php-fpm, mysql](https://www.linode.com/docs/web-servers/nginx/serve-php-php-fpm-and-nginx/)

##### php-fpm 常用命令
- `/etc/init.d/php-fpm start`
- `/etc/init.d/php-fpm stop`
- `/etc/init.d/php-fpm reload`

- [php-fpm配置](http://php.net/manual/zh/install.fpm.configuration.php)

##### nginx，cgi, fastcgi，php-fpm之间的关系
- [Nginx与FPM的工作机制](https://zhuanlan.zhihu.com/p/20694204)
#### nginx与uwsgi









### nginx原理分析


- **lingering_close：**
lingering_close，字面意思就是延迟关闭，也就是说，当nginx要关闭连接时，并非立即关闭连接，而是先关闭tcp连接的写，再等待一段时间后再关掉连接的读。为什么要这样呢？我们先来看看这样一个场景。nginx在接收客户端的请求时，可能由于客户端或服务端出错了，要立即响应错误信息给客户端，而nginx在响应错误信息后，大分部情况下是需要关闭当前连接。nginx执行完write()系统调用把错误信息发送给客户端，write()系统调用返回成功并不表示数据已经发送到客户端，有可能还在tcp连接的write buffer里。接着如果直接执行close()系统调用关闭tcp连接，内核会首先检查tcp的read buffer里有没有客户端发送过来的数据留在内核态没有被用户态进程读取，如果有则发送给客户端RST报文来关闭tcp连接丢弃write buffer里的数据，如果没有则等待write buffer里的数据发送完毕，然后再经过正常的4次分手报文断开连接。所以,当在某些场景下出现tcp write buffer里的数据在write()系统调用之后到close()系统调用执行之前没有发送完毕，且tcp read buffer里面还有数据没有读，close()系统调用会导致客户端收到RST报文且不会拿到服务端发送过来的错误信息数据。那客户端肯定会想，这服务器好霸道，动不动就reset我的连接，连个错误信息都没有。
在上面这个场景中，我们可以看到，关键点是服务端给客户端发送了RST包，导致自己发送的数据在客户端忽略掉了。所以，解决问题的重点是，让服务端别发RST包。再想想，我们发送RST是因为我们关掉了连接，关掉连接是因为我们不想再处理此连接了，也不会有任何数据产生了。对于全双工的TCP连接来说，我们只需要关掉写就行了，读可以继续进行，我们只需要丢掉读到的任何数据就行了，这样的话，当我们关掉连接后，客户端再发过来的数据，就不会再收到RST了。当然最终我们还是需要关掉这个读端的，所以我们会设置一个超时时间，在这个时间过后，就关掉读，客户端再发送数据来就不管了，作为服务端我会认为，都这么长时间了，发给你的错误信息也应该读到了，再慢就不关我事了，要怪就怪你RP不好了。当然，正常的客户端，在读取到数据后，会关掉连接，此时服务端就会在超时时间内关掉读端。这些正是lingering_close所做的事情。协议栈提供 SO_LINGER 这个选项，它的一种配置情况就是来处理lingering_close的情况的，不过nginx是自己实现的lingering_close。lingering_close存在的意义就是来读取剩下的客户端发来的数据，所以nginx会有一个读超时时间，通过lingering_timeout选项来设置，如果在lingering_timeout时间内还没有收到数据，则直接关掉连接。nginx还支持设置一个总的读取时间，通过lingering_time来设置，这个时间也就是nginx在关闭写之后，保留socket的时间，客户端需要在这个时间内发送完所有的数据，否则nginx在这个时间过后，会直接关掉连接。当然，nginx是支持配置是否打开lingering_close选项的，通过lingering_close选项来配置。 那么，我们在实际应用中，是否应该打开lingering_close呢？这个就没有固定的推荐值了，如Maxim Dounin所说，lingering_close的主要作用是保持更好的客户端兼容性，但是却需要消耗更多的额外资源（比如连接会一直占着）。

### 相关文档
- [Nginx入门指南](http://wiki.jikexueyuan.com/project/nginx/)
- [陶辉笔记](http://www.taohui.pub/622.html)
- [Nginx Mysql PHP 安装](https://www.jianshu.com/p/79942f37b2dc)

### 待阅读
- https://www.nginx.com/resources/wiki/start/topics/tutorials/commandline/