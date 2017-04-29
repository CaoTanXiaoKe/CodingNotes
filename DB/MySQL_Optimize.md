// MySQL 优化

##### MySQL 索引
MySQL 支持多种存储引擎，而各种存储引擎对索引的支持也各不相同，因此可以说MySQL数据库支持多种索引类型，如BTree索引，哈希索引，全文索引等等。 其中BTree索引最为常用。 对于BTree在读取磁盘上面的高效应用，《算法导论》中有精彩的讲解。 

MySQL中常用的两个存储引擎：MyISAM 和 InnoDB数据存储引擎都是以**B+树**为索引结构的。


##### MyISAM 和 InnoDB的不同之处
- MyISAM索引文件和数据文件是分离的。 MyISAM的叶节点的data域存放的是数据记录的地址。InnoDB的数据文件本身就是索引文件，InnoDB的叶节点data域保存了完整的数据记录。 InnoDB的key是数据表的主键，即InnoDB表数据文件本身就是主索引。 InnoDB的这种索引方式叫做**聚集索引**，另外，因为InnoDB的数据文件本身要按主键聚集，所以InnoDB要求表必须有主键（MyISAM可以没有），如果没有显式指定，则MySQL系统回自动选择一个可以唯一标识数据记录的列作为主键，如果不存在这种列，则MySQL自动为InnoDB表生成一个隐含字段作为主键，这个字段长度为6字节，类型为长整形。
- MyISAM索引的辅助索引data域存储的是数据记录的地址， InnoDB的辅助索引data域存储相应记录的主键值而不是地址。即：InnoDB的所有辅助索引都引用主键作为data域。 

因此： InnoDB以聚集索引的这种实现方式使得按主键搜索十分高效，但是辅助索引却需要检索两遍索引：首先检索辅助索引获得主键，然后用主键到主索引中检索获得记录。 因此，我们也可以知道：InnoDB不应该过长，而且InnoDB表主键设为自增是个不错的选择。 

#### 索引使用策略及优化
- 最左前缀匹配
- 查询条件中含有函数或表达式时， MySQL不会为这些列使用索引。 
- 有时为了效果的折中，可以考虑使用为列的部分建立索引，即前缀索引。

#### 索引选择性
- 在使用InnoDB存储引擎时，如果没有特别的需要，最好使用一个与业务无关的自增字段作为主键。 

#### 优化建议20+
1. 为查询缓存优化你的查询。
2. EXPLAIN你的SELECT查询。
3. 当只需要一行数据时使用 `LIMIT 1`。
4. 为搜索经常字段建索引。
5. 在Join表的时候使用相当类型的列，并将其索引。 
6. 千万不要ORDER BY RAND()
7. 避免SELECT *
8. 永远为每张表设置一个ID(最好是自增的)
9. 使用 ENUM 而不是 VARCHAR
10. 从 PROCEDURE ANALYSE() 取得建议
11. 尽可能的使用 NOT NULL
12. Prepared Statments
13. 无缓冲的查询
14. 把 IP 地址存成 UNSIGNED INT 
15. 固定长度的表会更快
16. 垂直分割
17. 拆分大的DELETE或INSERT语句
18. 越小的列会越快
19. 选择正确的存储引擎。 MyISAM更擅长查询，InnoDB更擅长插入和删除。 
20. 使用一个对象映射器（Object Relational Mapper）
21. 小心使用“永久链接”


#### 资料
- [MySQL索引背后的数据结构及算法原理 ](http://blog.codinglabs.org/articles/theory-of-mysql-index.html)
- [COOLSHELL|MySQL性能优化的最佳20+条经验](http://coolshell.cn/articles/1846.html)