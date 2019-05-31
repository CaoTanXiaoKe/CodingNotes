### 代理URI与服务器URI的不同
客户端向web服务器发送请求时，请求行中只包含部分URI（没有方案，主机或端口）。如：
```
GET /index.html HTTP/1.0
User-Agent: SuperBrowser v1.3
```
但当客户端向代理发送请求时，请求行中则包含完整的URI。如：
```
GET http://www.chenwenke.cn/index.html HTTP/1.0
User-Agent: SuperBrowser v1.3
```

这是因为客户端与代理服务器建立TCP连接之后，如果不包含完整的请求URI，代理服务器有可能不知道该向哪个Web服务器转发请求。这个问题和虚拟主机类似，一台物理Web服务器上可能有多个虚拟Web服务器站点，当包含部分URI的请求到达时，虚拟主机Web服务器需要知道目的Web站点的主机名。 尽管代理服务器和虚拟主机面临的问题类似，但是解决方案却不同：
- 显式的代理要求在请求报文中使用完整的URL来解决这个问题。 
- 虚拟主机Web服务器要求使用Host首部来承载主机和端口信息。 

对于代理服务器来说，使用完整和部分URI的规则如下：
- 如果提供的是完整URI，代理服务器就应该使用这个完整的URI。
- 如果提供的是部分URI，而且有Host首部，就应该用Host首部来确定原始服务器的名字和端口号。 
- 如果提供的是部分URI，而且没有Host首部，就要用其他方法来确定原始服务器。 
    - 如果代理是代表原始服务器的替代物，可以用真实服务器的地址和端口来配置代理。
    - 如果流量被拦截了，而且拦截者也可以提供原始的Ip地址和端口号，代理就可以使用拦截技术提供的IP和端口号。
    - 如果所有方法都失败了，代理服务器没有足够的信息来确定原始服务器，就必须返回一条错误报文（通常是建议用户升级到支持Host首部的现代浏览器）。
    - 
    - 

### Cookies 与 Session


- [参考资料](https://www.cnblogs.com/endlessdream/p/4699273.html)

### HTTPS
HTTPS 相较于HTTPS主要多两个功能：
- 对数据进行加密，并建立一个安全信息通道，来保证传输过程中的数据安全。
- 对网站服务器进行真实身份认证。 

#### TLS/SSL 工作原理
TLS/SSL的功能实现主要依赖于三类基本算法: 散列函数 Hash, 对称加密和非对称加密，其中使用非对称加密实现身份认证和密钥协商，对称加密算法采用协商的密钥对数据加密，基于散列函数验证信息的完整性。 
![image](https://www.wosign.com/News/images/2016030902.gif)

- 散列函数Hash
常见的hash函数有 MD5，SHA1, SHA256,该类函数的特点是**单向不可逆，对输入非常敏感，输出长度固定**，几乎对数据的任何修改都会改变散列函数的结果，用于放置信息篡改并验证数据的完整性。
在信息传输过程中，散列函数不能单独实现信息防篡改，因为明文传输，中间人可以修改信息之后重新计算信息摘要，因此需要对传输的信息及信息摘要进行加密。 
- 对称加密
常见的有AES-CBC，DES，3DES，AES-GCM等，相同的密钥同时用于信息的加密和解密，掌握密钥才能获取信息，能够防止信息窃听，通信方式1对1。
对称加密的优势是信息1对1，需要共享相同的密码，密码的安全是保证信息安全的基础，服务器和N个客户端通信，需要维护N个密码记录，且缺少修改密码的机制。 
- 非对称加密
常见有RSA算法，其他还有ECC，DH等算法。密钥成对出现，分别为公钥和私钥，公钥加密的信息只能私钥解开，私钥加密的信息只能公钥解开。因此掌握公钥的不同客户端之间不能相互解密信息，而只能和掌握私钥的服务器进行加密通信，服务器可以实现1对多的通信，客户端也可以用来验证掌握私钥的服务器身份。 
非对称加密的特点是信息传输1对多，服务器只需要维持一个私钥就能和多个客户端进行加密通信，但服务器发出的信息能够被所有客户端解密，但该算法计算复杂，加密速度慢。 

结合三类算法的特点，TLS算法的基本工作方式是，客户端使用非对称加密与服务器进行通信，实现身份验证并协商对称密码使用的密钥，然后对称加密算法采用协商密钥对信息以及信息摘要进行加密通信，不同的节点之间采用的对称密钥不同，从而可以确保信息只能通信双方获取。 

#### PKI体系
术语公钥基本结构(PKI, Public Key Infrastructure)用于描述管制或操纵证书与公钥及私钥的策略，标准和软件。实际上，PKI是指由数字证书，证书颁发机构(CA, Certificate Authority)以及对电子交易所涉及各方的合法性进行检查和验证的其他注册机构组成的一套系统。PKI的有关标准仍处于不断发展之中，即使这些标准已被作为电子商务的要素而广泛实施。 、

客户端得到服务器端公钥的过程中可能遭遇中间人攻击，或者由于中间人攻击的存在，服务器端可以信息抵赖。因此为了确保获取公钥的途径是合法的，能够验证服务器的身份信息，为此引入了权威的第三方机构**CA(Certificate Authority)**。CA负责核实公钥拥有者的信息，并颁发认证“证书”，同时能够为使用者提供证书验证服务，即**PKI体系(Public key infrastructure)**。
基本原理为，CA负责审核信息，然后对关键信息利用私钥进行“签名”，公开对应的公钥，客户端可以利用公钥验证签名。CA也可以吊销已经签发的证书，吊销证书的方式一般包括两类，分别是使用 CRL文件和OCSP。CA使用具体的流程如下：
![image](https://www.wosign.com/News/images/20160309032.png)
1. 服务器S向第三方机构CA提供公钥，组织信息，个人信息（域名）等信息申请认证；
2. CA通过线上，线下等多种手段验证申请者提供信息的真实性，如组织是否存在，企业是否合法，是否拥有域名的所有权等；
3. 如果信息审核通过，CA会向申请者签发认证文件-证书:
    - 证书包含以下信息：申请者公钥，申请者的组织信息和个人信息，签发机构CA的信息，有效时间，证书序列号等信息的明文，同时包含一个签名：
        - 签名的产生： 首先，使用散列函数计算公开的明文信息的摘要，然后，采用CA的私钥对信息摘要进行加密，密文即签名。 
4. 客户顿 C 向服务器 S 发出请求时，S 返回证书文件；
5. 客户端 C 读取证书中的相关的明文信息，采用相同的散列函数处理这些明文信息得到信息摘要，然后，利用对应CA的公钥解密签名数据，对比证书的信息摘要，如果一致，则可以确认证书的合法性，即服务器端的公钥合法。 
6. 客户端然后验证证书相关的域名信息，有效时间等信息；
7. 客户端会内置信任CA的证书信息(包含公钥)，如果CA不被信任，则找不到对应CA的证书，证书也会被判定非法。 

**这个过程中注意这样几点：**
1. 申请证书不需要提供私钥，确保私钥永远只能服务器掌握。 
2. 证书的合法性仍然依赖于非对称加密算法(客户端通过CA的公钥解密签名，对比摘要的方式确认证书的确是CA颁发的)， 除了服务器公钥之外，证书中还包括了服务器信息和域名信息等。 
3. 内置CA对应的证书称为根证书，颁发者和使用者相同，自己为自己签名，即自签名证书。
4. 证书 = 公钥 + 申请者与发布者信息 + 签名

##### 证书链
CA根证书和服务器证书中间可以有一级或多级证书，即中间证书。证书的产生和验证原理不变，只是增加了一层或多层验证，只要最终能够被任何信任的CA根证书验证合理即可。

1. 客户端内置了信任CA的root.pem证书(常见的浏览器和操作系统都内置了信任CA的根证书)，客户端从服务器端得到中间证书inter.pem和服务器证书server.pem证书。
2. 客户端用CA的根证书公钥解密和验证根证书的合法性，即验证了中间证书inter.pem的公钥是否可信。
3. 然后用中间证书的公钥解密和验证inter.pem证书的合法性，即验证了服务器server.pem公钥是否可信。如果server.pem可信，然后用服务器端的公钥来认证服务器端的身份及和服务器端协商对称加密密钥。 

**二级/多级证书结构的好处：**
- 减少根证书机构的管理工作量，可以更高效的进行证书的审核和签发；
- 根证书一般内置在客户端中，私钥一般离线存储，一旦私钥泄露，则吊销过程非常困难，无法及时补救。证书链越长，根证书的密钥越不易被攻破。
- 中间证书结构的私钥泄露，则可以快速在线吊销，并重新为用户签发新的证书。
- 证书链四级以内一般不会对HTTPS的性能造成明显影响。 
![image](https://www.wosign.com/News/images/20150309034.png)

**证书吊销：**<br/>
CA机构能够签发证书，同样也存在机制宣布以往签发的证书无效。例如证书使用者不合法，CA废弃该证书；或者使用者私钥泄露，使用者申请让证书无效。现在存在两证吊销证书的机制：
- CRL(Certificate Revocation List), 证书吊销列表。CRL是一个单独的文件。该文件包含了CA已经吊销的证书序列号（唯一）与吊销日期，同时该文件包含生效日期并通知下次更新该文件的时间，当然该文件必然包含用CA私钥加密的签名以验证文件的合法性。
    - 证书中一般会包含一个URL地址 CRL Distribution Point，通知使用者去哪里下载对应的CRL以校验证书是否已被吊销。该吊销方式的优点是不需要频繁更新，但是缺点是不能及时吊销证书，因为CRL更新时间一般是几天，这期间可能已经造成了极大的损失。
- OCSP(Online Certificate Status Protocal), 证书状态在线查询协议，一种实时查询证书是否已经被吊销的方式。请求者发送证书的信息并请求查询，服务器返回正常，吊销或未知中的任何一个状态。证书中一般也会包含一个OCSP的URL地址，要求查询服务器具有良好的性能。部分CA或大部分的自签CA（根证书）都是未提供CRL或OCSP地址的，对于吊销这些证书是一件非常麻烦的事情。

#### SSL/TLS 握手概述
- [关于SSL/TLS的详细信息，实现细节的RFC文档](https://tools.ietf.org/html/rfc5246)
客户端验证服务器身份并与服务器协商对称密钥的过程称为SSL/TLS的握手阶段。 
![image](http://www.ruanyifeng.com/blogimg/asset/201402/bg2014020502.png)
---
![image](https://www.wosign.com/News/images/20160309041.png)
---
“握手阶段”一般涉及四次通信，并且这四次通信的数据大部分都是明文传输（有部分字段会加密）
1. 客户端(通常是浏览器)想服务器发出加密通信的请求，被称为`client_hello`请求。这一步客户端主要向服务器提供一下信息：
    - 支持的最高TSL协议版本version，从低到高依次为 SSLv2, SSLv3, TLSv1, TLSv1.1, TLSv1.2, 当前基本不再使用低于TLSv1的版本；
    - 客户端支持 的加密套件 cipher suites列表，每个加密套件对应TLS原理中的四个功能的组合: 认证算法Au（身份验证）, 密钥交换算法 KeyExchange（密钥协商），对称加密算法Enc(信息加密)和信息摘要Mac(完整性校验);
    - 支持的压缩算法 compressing methods 列表, 用于后续的信息压缩传输;
    - 随机书 random_C，用于后续密钥的生成;
    - 扩展字段 extensions，支持协议与算法的相关参数以及其它辅助信息等，常见的SNI就属于扩展字段。
        - 由于此时客户端发送的信息之中不包括服务器域名，也就是说，理论上服务器只能包含一个网站，否则会分不清该向客户端提供哪一个网站的数字证书。这就是为什么通常一台服务器只能有一张数字证书的原因。对于虚拟机的用户来说，这一点很不方便。因此 2006年，TLS协议加入了一个 [Server Name Indication扩展](https://tools.ietf.org/html/rfc4366)， 允许客户端向服务器提供它所请求的域名。  
2. 服务器收到客户端请求后，向客户端发出回应`server_hello + server_certificate+server_hello_done`，包含以下内容:
    - 其中server_hello中包含以下内容: 
        - 选择使用的协议版本version，比如 TLS 1.2; 
        - 选择的加密套件 cipher suite。
        - 选择的压缩算法 compression method。
        - 随机数 random_S等，random_S 用户后续的密钥协商。 
    - server_certificates, 服务器端配置的对应的证书链，用于身份验证与密钥交换。 
    - server_hello_done，通知客户端server_hello 信息发送结束; 
    

除了上面的这些信息外，如果服务器需要确认客户端的身份，就会再包含一项请求，要求客户端提供“客户端证书”。比如，金融机构往往只允许认证客户连入自己的网络，他们会向正式客户提供USB密钥，里面包含了一张客户端证书。 
3. 证书校验。客户端收到服务器端的回应后，开始校验服务器证书的合法性，如果验证通过才会进行后续通信，否则根据错误情况不同做出提示和操作，合法性验证包括以下几点:
    - 证书链的可信性 trusted certificate path, 使用根CA证书的公钥进行解密签名，进行摘要对比。依次认证服务器证书。 
    - 证书是否已经被吊销 revocation，两类验证方式: 离线CRL和在线OCSP，不同的客户端行为可能会有所不同。 
    - 有效期 expiry date，证书是否在有效时间范围；
    - 域名 domain，检查证书域名是否与当前的访问域名匹配。
4. 客户端验证完服务器证书合法后，客户端从服务器证书中取出公钥，然后向服务器发送这些信息`client_key_exchange + change_cipher_spec + encrypted_handshake_message`：
    - client_key_exchange, 合法性验证通过之后，客户端计算产生随机数字Pre-master, 并用证书公钥加密，发送给服务器。
    - 此时客户端已经获取了计算协商密钥所需要的全部信息： 两个明文随机数 random_C 和 random_S 与自己计算产生的随机数 Per-master，可以通过计算得到协商密钥: enc_key=Fuc(random_C, random_S, pre-Master)
        - 为什么使用三个随机数: 不管是客户端还是服务器，都需要随机数，这样生成的密钥才不会每次都一样。由于SSL协议中证书是静态的，因此十分有必要引入一种随机因素来保证协商出来的密钥的随机性。对于RSA密钥交换算法来说，pre-master-key本身就是一个随机数，再加上hello消息中的随机，三个随机数通过一个密钥导出器最终导出一个对称密钥。pre master的存在在于SSL协议不信任每个主机都能产生完全随机的随机数，如果随机数不随机，那么pre master secret就有可能被猜出来，那么仅适用pre master secret作为密钥就不合适了，因此必须引入新的随机因素，那么客户端和服务器加上pre master secret三个随机数一同生成的密钥就不容易被猜出了，一个伪随机可能完全不随机，可是是三个伪随机就十分接近随机了，每增加一个自由度，随机性增加的可不是一。
    - change_cipher_spec, 编码改变通知，客户端通知服务器后续的通信都采用协商的通信密钥和加密算法加密通信。 
    - encrypted_handshake_message, 计算之前所有通信参数的hash值与其它相关信息生成一段数据，采用协商密钥session secret与加密算法进行加密，然后发送给服务器用户数据和握手验证。 

(如果上面拂去其要求了客户端提供证书，客户端会在这一步发送证书及相关信息。)
5. 服务器端验证完相关信息后，发送`change_cipher_spec + encrypted_handshake_message`给客户端。
    - 服务器用私钥解密加密的Pre-master数据，基于之前交换的两个明文随机数 random_C 和 random_S ，计算得到协商密钥：enc_key=Fuc(random_C, random_S, Pre-Master)； 
    - 计算之前所接收信息的hash值，然后解密客户端发送的encrypted_handshake_message, 验证数据和密钥的正确性；
    - change_cipher_spec, 验证通过之后，服务器同样发送change_cipher_spec以告知客户端后续的通信都采用协商的密钥session secret和加密算法进行通信； 
    - encrypted_handshake_message, 服务器也结合所有当前的通信参数信息生成一段数据并采用协商密钥session secret与加密算法加密后发送给客户端。
6. 客户端计算所有接收信息的hash值，并采用协商密钥解密 encrypted_handshake_message, 验证服务器发送的数据和密钥，验证通过则握手完成。 后续即使用协商好的密钥： session secret 和协商好的加密算法进行加密通信。 
![image](http://www.ruanyifeng.com/blogimg/asset/201402/bg2014020503.gif)
---
- 参考资料
    - [HTTPS加密协议详解（四）: TLS/SSL握手过程](https://www.wosign.com/faq/faq2016-0309-04.htm) 
    - [SSL/TLS协议运行机制的概述](http://www.ruanyifeng.com/blog/2014/02/ssl_tls.html)
    - [大型网站的HTTPS实践](https://developer.baidu.com/resources/online/doc/security/https-pratice-1.html)

### openssl 的用法

OpenSSL主要包括以下三个组件：
- openssl： 多用途的命令行工具。
- libcryto： 加密算法库。
- libssl： 加密模块应用库，实现了ssl及tls。

#### 对称加密
对称加密使用的标准命令为 `enc`， 用法如下：
```
openssl enc -ciphername [-in filename] [-out filename] [-pass arg] [-e] [-d] [-a/-base64]
       [-A] [-k password] [-kfile filename] [-K key] [-iv IV] [-S salt] [-salt] [-nosalt] [-z] [-md]
       [-p] [-P] [-bufsize number] [-nopad] [-debug] [-none] [-engine id]
```

常用选项有： 
- `-in filename`：指定要加密的文件存放路径
- `-out filename`：指定加密后的文件存放路径
- `-salt`：自动插入一个随机数作为文件内容加密，默认选项
- `-e`：可以指明一种加密算法，若不指的话将使用默认加密算法
- `-d`：解密，解密时也可以指定算法，若不指定则使用默认算法，但一定要与加密时的算法一致
- `-a/-base64`：使用-base64位编码格式

**示例**：
```
// 加密：
>$ openssl enc -e -des3 -a -salt -in text -out miwen
// 解密：
>$ openssl enc -d -des3 -a -salt -in miwen -out mingwen
```

#### 单向加密
单向加密需要使用的标准命令为 dgst ，用法如下：
```
openssl dgst [-md5|-md4|-md2|-sha1|-sha|-mdc2|-ripemd160|-dss1] [-c] [-d] [-hex] [-binary]
       [-out filename] [-sign filename] [-keyform arg] [-passin arg] [-verify filename] [-prverify
       filename] [-signature filename] [-hmac key] [file...]
```

常用选项有：
- `[-md5|-md4|-sha1|-sha|-ripemd160|-dss1]`：指定一种加密算法
- `-out filename`：将加密的内容保存到指定文件中

**示例**：
```
>$ openssl dgst -md5 text 
>$ openssl dgst -out miwen -md5 text 
>$ openssl dgst -md4 text 
>$ openssl dgst -sha1 text 
>$ openssl dgst -sha text 
>$ openssl dgst -ripemd160 text 
>$ openssl dgst -dss1 text 
```

除了 openssl dgst 工具外，其他常用的单向加密工具：
- `md5sum` `sha1sum` `sha224sum` `sha256sum` `sha384sum` `sha512sum`

**示例**：
```
>$ md5sum text 
>$ sha1sum text 
>$ sha224sum text 
>$ sha256sum text 
>$ sha384sum text 
>$ sha512sum text
```

#### 生成密码
生成密码使用的标准命令为 `passwd` ，用法如下：
```
openssl passwd [-crypt] [-1] [-apr1] [-salt string] [-in file] [-stdin] [-noverify] [-quiet] [-table] {password}
```

常用选项有：

- `-1`：使用md5加密算法
- `-salt string`：加入随机数，最多8位随机数
- `-in file`：对输入的文件内容进行加密
- `-stdion`：对标准输入的内容进行加密

**示例**:
```
>$ echo "gen password" | openssl passwd -1 -stdin
>$ openssl passwd -1 -in text -salt 12345678
>$ openssl passwd -1 -in text -salt 12345
>$ openssl passwd -crypt -in text -salt 12345
>$ openssl passwd -apr1 -in text -salt 12345
```

#### 生成随机数

生成随机数使用的标准命令是 `rand`, 用法如下：
```
openssl rand [-out file] [-rand file(s)] [-base64] [-hex] num
```

常用选项有：
- `-out file`：将生成的随机数保存至指定文件中
- `-base64`：使用base64 编码格式
- `-hex`：使用16进制编码格式

**示例**：
```
>$ openssl rand -hex 10
>$ openssl rand -base64 5
>$ openssl rand -base64 5 -out mima
```

#### 生成密钥对
先使用标准命令`genrsa`生成私钥，然后再使用 `rsa` 标准命令从私钥中提取公钥。

`genras`的用法如下：
```
openssl genrsa [-out filename] [-passout arg] [-des] [-des3] [-idea] [-f4] [-3] [-rand file(s)] [-engine id] [numbits]
```

常用选项：
- `-out filename`：将生成的私钥保存至指定的文件中
- `-des|-des3|-idea`：不同的加密算法
- `numbits`：指定生成私钥的大小，默认是2048

**密钥文件的访问权限需要控制好，一般只能自己读写。 可以用 `umask`命令设置所要生成的私钥的权限。** 

**示例**：
```
>$ (umask 077; openssl genrsa -out siyao 4096)
```

`rsa`的用法如下：
```
openssl rsa [-inform PEM|NET|DER] [-outform PEM|NET|DER] [-in filename] [-passin arg] [-out filename] [-passout arg]
       [-sgckey] [-des] [-des3] [-idea] [-text] [-noout] [-modulus] [-check] [-pubin] [-pubout] [-engine id]
```

常用选项：
- `-in filename`：指明私钥文件
- `-out filename`：指明将提取出的公钥保存至指定文件中 
- `-pubout`：根据私钥提取出公钥 

**示例**：
```
>$ openssl rsa -in siyao -out gongyao -pubout
```

#### 非对称加密与解密
使用标准命令 `rsautl` 进行加密，解密，签名和验证。

`rsautl`的用法如下：
```
openssl rsautl [-in file] [-out file] [-inkey file] [-pubin] [-certin]
       [-sign] [-verify] [-encrypt] [-decrypt] [-pkcs] [-ssl] [-raw]
       [-hexdump] [-asn1parse]
```

常用选项： 
- `-in filename`: 要加密或签名的文件。
- `-out filename`: 存放密文或签名结果的文件。
- `-inkey file`: 加密时，用于加密的密钥。
- `-pubin`: 指定用公钥进行加密，或验证。 
- `-certin`: 指定用证书中的公钥，进行加密或验证。
- `-encrypt`: 加密。
- `-decrypt`: 解密。
- `-sign`: 签名。
- `-verify`: 验证。

**加密与解密示例**： 
```
// 用 siyao中的公钥进行加密
>$ openssl rsautl -encrypt -in text -inkey siyao -out enc.txt
// 用 siyao中的私钥进行解密
>$ openssl rsautl -decrypt -in enc.txt -inkey siyao
// 清空 enc.txt文件
>$ > enc.txt
// 用 gongyao中的公钥进行加密
>$ openssl rsautl -encrypt -in text -inkey gongyao -pubin -out enc.txt
// 用 siyao 中的私钥进行解密
>$ openssl rsautl -decrypt -in enc.txt -inkey siyao

// 用证书中的公钥进行加密
>$ openssl rsautl -encrypt -in text -certin -inkey 1_www.chenwenke.cn_bundle.crt -out enc.txt
// 用证书的私钥进行解密
>$ openssl rsautl -decrypt -in enc.txt -inkey 2_www.chenwenke.cn.key
```

**签名与验证示例**：
```
// 用 siyao进行签名，实际上是用其中的私钥进行加密。
>$ openssl rsautl -sign -in text -inkey siyao -out sign.txt
// 用 siyao进行验证，实际上是用其中的公钥进行解密。
>$ openssl rsautl -verify -in sign.txt -inkey siyao
// 用 gongyao进行验证，实际上是用公钥进行解密。
>$ openssl rsautl -verify -in sign.txt -inkey gongyao -pubin

// 签名，base64编码
>$ openssl rsautl -sign -in text -inkey siyao  | base64 > sign.txt
// base64解码，验证
>$ cat sign.txt | base64 -d | openssl rsautl -verify -inkey gongyao -pubin

// 用证书的私钥进行签名
>$ openssl rsautl -sign -in text -inkey 2_www.chenwenke.cn.key -out sign.txt 
// 用证书中的公钥进行验证
>$ openssl rsautl -verify -in sign.txt -certin -inkey 1_www.chenwenke.cn_bundle.crt
```

#### 证书格式
- [SSL证书的格式](https://www.cnblogs.com/guogangj/p/4118605.html)