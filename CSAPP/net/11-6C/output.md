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

由报头信息可知， 使用的 HTTP 版本是 1.1。

#### HTTP/1.0 与 HTTP/1.1 的区别

现在最新的HTTP版本是 HTTP/1.1。 HTTP/1.0是从 1996 年沿用至今的老版本。HTTP/1.1定义了一些附加的报头，为诸如缓冲和安全等高级特性提供支持，它还支持一种机制，允许客户端和服务器在同一条持久连接上执行多个事务。实际上，两个版本是相互兼容的，因为 HTTP/1.0的客户端和服务器会简单地忽略 HTTP/1.1 的报头。

---

>
- Proxy support and the Host field:

HTTP 1.1 has a required Host header by spec.

HTTP 1.0 does not officially require a Host header, but it doesn't hurt to add one, and many applications (proxies) expect to see the Host header regardless of the protocol version.

Example:

GET / HTTP/1.1
Host: www.blahblahblahblah.com
This header is useful because it allows you to route a message through proxy servers, and also because your web server can distinguish between different sites on the same server.

So this means if you have blahblahlbah.com and helohelohelo.com both pointing to the same IP. Your web server can use the Host field to distinguish which site the client machine wants.

- Persistent connections:

HTTP 1.1 also allows you to have persistent connections which means that you can have more than one request/response on the same HTTP connection.

In HTTP 1.0 you had to open a new connection for each request/response pair. And after each response the connection would be closed. This lead to some big efficiency problems because of TCP Slow Start.

- OPTIONS method:

HTTP/1.1 introduces the OPTIONS method. An HTTP client can use this method to determine the abilities of the HTTP server. It's mostly used for Cross Origin Resource Sharing in web applications.

- Caching:

HTTP 1.0 had support for caching via the header: If-Modified-Since.

HTTP 1.1 expands on the caching support a lot by using something called 'entity tag'. If 2 resources are the same, then they will have the same entity tags.

HTTP 1.1 also adds the If-Unmodified-Since, If-Match, If-None-Match conditional headers.

There are also further additions relating to caching like the Cache-Control header.

- 100 Continue status:

There is a new return code in HTTP/1.1 100 Continue. This is to prevent a client from sending a large request when that client is not even sure if the server can process the request, or is authorized to process the request. In this case the client sends only the headers, and the server will tell the client 100 Continue, go ahead with the body.

- Much more:

	- Digest authentication and proxy authentication
	- Extra new status codes
	- Chunked transfer encoding
	- Connection header
	- Enhanced compression support
	- Much much more.
