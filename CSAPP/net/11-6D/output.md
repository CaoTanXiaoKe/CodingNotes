#### 输出的报头

>
GET /rio.h HTTP/1.1
Host: localhost:8000
Connection: keep-alive
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/53.0.2785.143 Chrome/53.0.2785.143 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Encoding: gzip, deflate, sdch
Accept-Language: en-US,en;q=0.8


---

第一行是**请求行(request line)**，后面跟零个或更多个**请求报头(request header)**, 再跟随一个空的文本行来终止报头列表。 

- 第一行： 这是**请求行(request line)**，可以分成三部分 <method> <uri> <version>
- 第二行： Host 报头在 HTTP/1.1 中是需要的，在 HTTP/1.0中是不需要的。代理缓存会使用Host报头，Host报头指明原始服务器。 
- 第三行： 表明浏览器支持 持久连接， 也就是说支持使用同一个 TCP连接来发送和接收多个 HTTP请求/应答，而不是为每个新的请求/应答打开新的连接。顺便说一下，HTTP/1.1 支持 持久连接，HTTP/1.0不支持。 
- 第四行：可以使用线路上的缓存。 
- 第五行：The HTTPS HTTP request header field sends a signal to the server expressing the client’s preference for an encrypted and authenticated response, and that it can successfully handle the upgrade-insecure-requests directive in order to make that preference as seamless as possible to provide.
- 第六行：告诉服务器，客户端浏览器，操作系统信息。 
- 第七行：告诉服务器，浏览器支持的字符集。 
- 第八行：告诉服务器，浏览器支持的语言。
