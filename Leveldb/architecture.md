####  整体框架



![img](https://pic002.cnblogs.com/images/2011/274814/2011121116344075.png)



##### .log文件

`.log`文件的作用类似于MySQL里的`binlog`文件，在写入(删除)一条数据的时候，首先把命令记录写入`.log`文件。使得即使程序崩溃，或者机器崩溃，数据仍旧不会丢失。(极端情况下还是有可能丢失的，下文详诉)。









### 参考资料

- [Leveldb实现原理](https://www.cppfans.org/1652.html)

- [Leveldb源码类功能解析](https://cloud.tencent.com/developer/article/1191403)
- [LevelDB关键实现图解](https://juejin.im/entry/5852034c8e450a006c8658eb)
- [浅析BigTable和LevelDB的实现](https://www.open-open.com/lib/view/open1502592698749.html)
- [Leveldb之MANIFEST](http://bean-li.github.io/leveldb-manifest/)
- [Leveldb源码解析](https://zhuanlan.zhihu.com/p/34657032)
- [Leveldb实现分析](http://taobaofed.org/blog/2017/07/05/leveldb-analysis/)





