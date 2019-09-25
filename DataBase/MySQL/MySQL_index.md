## MySQL 索引



#### 对jion关联字段创建索引

join时被驱动表的关联字段有无索引对性能影响很大，mysql采用的是不同的join算法。下面分别介绍这两种场景join工作流程。



##### 被驱动表有索引

针对被驱动表有索引的情况，mysql采用的join算法叫做 Index Nested-Loop Join， 算法流程如下图：

![index nested-loop join工作流程](http://km.oa.com/files/photos/pictures/201906/1561369089_57_w992_h340.png)

如上，假如驱动表t1满足where条件的数据行数为N，被驱动表t2满足where条件的数据行为为M, 那么总的扫描行数为: N + N*logM，因此N越小，扫描的行数就越少，因此应该选择小表作为驱动表。这里小表的定义是: **按照where条件过滤之后行数最小的表即为小表。**

##### 被驱动表无索引



