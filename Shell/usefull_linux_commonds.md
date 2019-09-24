#  Linux命令

### 压缩与解压

##### Tar
filename.tar.gz 解压到 /usr/local 文件夹

`$ tar -zxf filename.tar.gz -C /usr/local`

##### GZ
解压 *.gz文件

`gunzip filename.gz`


#### Linux查看端口占用情况



#### 命令行快捷键

- `cd -` 进入上次访问的目录
- `ctr + r` 搜索历史命令 reverse-i-search
- `ESC + f` 向右移动一个单词， MAC下 `ALT + ->`
- `ESC + d` 向左移动一个单词， MAC下 `ESC + <-`
- `ctr + a` 跳到行首
- `ctr + e` 跳到行尾
- `ctr + w` 删除当前单词
- `ctr + u` 删除当前位置至行首
- `ctr + k` 删除当前位置至行尾
- `ctr + y` 插入最近删除的单词
- `ctr + l` 清屏

# rpmp
查找 OpenJDK的安装环境 
`
$ rpm -ql java-1.7.0-openjdk-devel | grep '/bin/javac'
`  

ln -s /usr/local/bin/python3.3 /usr/local/bin/python
      (source)                 (virtual path)

### vim
##### 替换
- 对当前行进行替换
    - `:s/abc/efg/`
    - `:s/abc/efg/g`
- 对所有行进行替换
    - `:%s/abc/efg/`
    - `:%s/abc/efg/g`
- 对从第n行开始向下的所有行进行替换，当n为`.`时，表示从当前行开始。
    - `:n,$s/abc/efg/`
    - `:n,$s/abc/efg/g`
- 备注： 上面的命令中，如果没有`g`表示只替换一行中第一次出现的字符串`abc`为`efg`。而后面带`g`的表示当前行的所有`abc`替换成`efg`。

### find
- 列出当前目录及子目录下所有文件和文件夹。
- `find .`
- 在`/home`目录下查找以`.txt`结尾的文件名。
- `find /home -name "*.txt"`
- 如： `find / -name 'php.ini'`
- 同上，但忽略大小写。
- `find /home -iname "*.txt"`
- 当前目录或子目录下查找所有以 `.txt`和`.pdf`结尾的文件。
- `find . \( -name "*.txt" -o -name "*.pdf" \)`
- `find . -name "*.txt" -o -name "*.pdf"`
- 匹配文件路径或文件。
- `find /usr/ -path "*local*"`
- 基于正则表达式匹配文件路径。
- `find . -regex ".*\(\.txt\|\.pdf\)$"`
- 把 win文档转换成linux下的文档
- `find ./ -type f -print0 | xargs -0 dos2unix`
- `find ./ -type f print0 | xargs -0 sed -i 's/^M$//'`
- 把linux 下的文档转换成 win下的
- `find ./ -type f -print0 | xargs -0 unix2dos`
- `fild ./ -type f print0 | xargs -0 sed -i 's/$/^M/'`

### ps
- 杀死进程
- `ps -ef | grep cronjob_main | grep -v grep | awk {'print $2'} | xargs kill -9`
- 
- 
### crontab任务
- 定时

令人惊讶的是，使用 awk, sed, grep, sort, uniq, xargs的组合，可以在几分钟内完成许多数据分析，并且他们的性能相当的好。 

### awk
- `awk '{print $2}'`: 输出第二列
- ```
   // 统计第七列出现最多的前五个词语
   cat /var/log/nginx/access.log |
   awk '{print $7}' |
   sort             |
   uniq -c          |
   sort -r -n       |
   head -n 5
   ```

### sed

- 结合sed，awk 进行计数统计
`grep "msg_num" logrus.log | sed 's/msg_num=\([^,&]*\)/\1/g' | awk '{print $4}' | awk '{sum += $1} END {print sum}'`

### 常用命令
- `uname -a` : 查看操作系统内核版本信息
- 
- [`netstat`](https://linux.cn/article-2434-1.html)
- `top`
- `strace`
- `pmap`
- `iostat`
- `dstat`

### Python

使用python的json.tool，以易读的方式显示JSON字符串

`
echo '{ "status":200,"data":[{"id":1000,"name":"John"},{"id":1004,"name":"Tom"}]}'|python -mjson.tool
`
#### Set Python Environment
`easy_install pip`
`pip install virtualenv`
`virtualenv -p /usr/bin/python2.7 my_project`
`source my_project/bin/activate`
`deactivate`

#### Linux 内核参数配置
位置： `/etc/sysctl.conf`
```
fs.file-max = 999999

```






### 监控命令
- `iostat` 生成CPU和IO设备的统计信息报表




### 在后台运行程序
- `nohup <command> &` 如： `nohup ping www.chenwenke.cn &`
- `setsid <command>` 如： `setsid ping www.chenwenke.cn`
- `(<command> &)` 如： `(ping www.chenwenke.cn &)`
- 使用 `screen`命令

- [参考资料](https://www.ibm.com/developerworks/cn/linux/l-cn-nohup/index.html)


### Linux 进程优先级与调度

- [参考资料](https://linux.cn/article-7325-1.html)

### Linux `/proc`目录
- [知乎专栏：/proc 目录](https://zhuanlan.zhihu.com/p/26923061)
- [linux 文档：/proc 目录]()
- [使用 /proc 文件系统来访问Linux内核内容](https://www.ibm.com/developerworks/cn/linux/l-proc.html)

### Linux 虚拟机的桥接与net



### iptables的使用

### `updatedb` 

### 调试`php-fpm`程序
`ps -ef|grep php-fpm|awk '{print " -p " $2" -s 10000 -tfv -o tmp/strace_"$2".log"}'|xargs strace`


### 系统日志配置目录： `/etc/rsyslog.conf`
- [如何对rsyslog进行配置](https://www.mtyun.com/library/how-to-config-rsyslog)
- [Understanding the /etc/rsyslog.conf file for configuring System Logging](https://www.thegeekdiary.com/understanding-the-etc-rsyslog-conf-file-for-configuring-system-logging/)
- [Linux环境下使用rsyslog管理日志](https://segmentfault.com/a/1190000003509909)
- [第十八章、認識與分析登錄檔](http://linux.vbird.org/linux_basic/0570syslog.php)
- 

#### Linux文件系统

- https://www.linuxprobe.com/linux-system-structure.html
- 
