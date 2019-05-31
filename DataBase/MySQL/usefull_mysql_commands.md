#### Mysql 本身命令
- **Start：**
    - `sudo /etc/init.d/mysql start`
- **Stop：**
    - `sudo /etc/init.d/mysql stop`
- **Restart/reload configs：**
    - `sudo /etc/init.d/mysql restart`
- **Check run status：**
    - `sudo /etc/init.d/mysql status`

---

- **启用通用查询日志（>= MySQL5.1）**
    - `SET GLOBAL general_log='on'`
- **查看通用查询日志**
    - `SELECT * FROM mysql.general_log \G`
- **关闭通用查询日志**
    - `SET GLOBAL general_log='off'` 

- **查看Mysql版本信息：**
    - `mysql> SELECT VERSION()`
- **查看正在链接的线程**
    - `show [full] processlist` 
    - [详细资料](https://dev.mysql.com/doc/refman/8.0/en/show-processlist.html)
- **中止链接或查询语句**
    - `KILL [CONNECTION | QUERY] processlist_id`
    - [详细资料](https://dev.mysql.com/doc/refman/8.0/en/kill.html)

#### Debug

- **查看优化后的SQL语句，及其查询计划和执行方式**
  - 先`EXPLAIN EXTENDED`，然后 `SHOW WARNINGS` 

### MySQL的事务特征
> Transactions start when a cursor execute a query, end when COMMIT or ROLLBACK is executed by the Connection object. [MySQLdb]

### MySQL 语句
- **修改表明：**
    - `ALTER TABLE table_name RENAME TO new_table_name`
    - 
- **查看SQL语句是如何被处理的：**
    - `EXPLAIN sql_str` 



### 优化

#### 查询优化

##### 分页优化`limit offset`
```
select * from member_InnoDB where gender=1 limit 1 offset 300000; 
```
**优化为：**
```
select a.* from member_InnoDB as a 
inner join
(select id from member_InnoDB where gender=1 limit 1 offset 300000) as b 
on a.id=b.id;
```

在数据库引擎是`InnoDB`时，查询过程为：
- 通过二级索引`gender`查到主键值(即：找出所有gender=1的id)。
- 再根据查到的主键值通过主键索引找到相应的数据块（根据id找出对应的数据块的内容）
- 根据offset的值，查询300001次主键索引的数据(不单单是索引本身)，然后将之前的300000条丢弃，取出最后1条。 

从上面可以看出，前300000条根据索引取相应的数据块的操作完全是浪费的。 为了避免这种浪费，可以先只取主键，根据主键再索引数据块。 


- **参考资料:**
- [Mysql InnoDB Limit Offset 优化](https://blog.csdn.net/fdipzone/article/details/72793837)
- [Mysql MyISAM Limit Offset 优化](https://explainextended.com/2009/10/23/mysql-order-by-limit-performance-late-row-lookups/)




#### 参考资料
- [MySQL 主键原理](http://blog.codinglabs.org/articles/theory-of-mysql-index.html)
- [MySQL性能优化的最佳20+条经验](https://coolshell.cn/articles/1846.html)



### 待验证
`UPDATE counters SET value = value + 1 WHERE key = 'foo';`

MySQL/InnoDB的可重复读并不会检测丢失更新。 一些作者认为， 数据库必须能防止丢失更新才称得上提供了快照隔离， 所以在这个定义下，MySQL下并不提供快照隔离。 