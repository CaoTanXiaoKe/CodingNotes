## 条件变量详解

关于条件变量，APUE中解释的不是太清楚。书上只是简单的描述了一下，很不清晰。


> 条件变量给多个线程提供了一个会合的场所。条件变量与互斥量一起使用时，允许线程以无竞争的方式等待特定的条件发生。<br/> 
条件本身是由互斥量保护的。线程在改变条件状态之前必须首先锁住互斥量。其他线程在获得互斥量之前不会察觉到这种改变，因为互斥量必须在锁定以后才能计算条件。 

> pthead_cond_wait 等待条件变为真。 pthread_cond_timedwait函数与pthread_cond_wait 函数类似，只是多了一个超时，如果在给定的时间条件不能满足，那么会生成一个返回错误码的变量。 


### 实例代码： [生产者-消费者队列完整代码](/2017/07/15/mutex-condition-producer-customer/)

```cpp
// 生产者线程核心代码
// QUEUESIZE 的值为： 100
pthread_mutex_lock(&m_mtx); 
while(m_log.size() >= QUEUESIZE)
{
	pthread_cond_wait(&m_notFull, &m_mtx); 
}
m_log.push_back(log); 
pthread_cond_signal(&m_notEmpty); 
// pthread_cond_broadcast(&m_notEmpty); 
pthread_mutex_unlock(&m_mtx); 



// 消费者线程核心代码
pthread_mutex_lock(&m_mtx); 
while(m_log.size() == 0)
{
	pthread_cond_wait(&m_notEmpty, &m_mtx); 
}
log = m_log.front(); 
m_log.pop_front(); 
pthread_cond_signal(&m_notFull);
// pthread_cond_broadcast(&m_notFull);  
pthread_mutex_unlock(&m_mtx); 
```


> 传递给 pthread_cond_wait 的互斥量对条件进行保护。调用者把锁住的互斥量传给函数， 函数然后自动把调用线程放到等待条件的线程列表上，对互斥量解锁。这就关闭了条件检查和线程进入休眠状态等待条件改变这两个操作之间的时间通道，这样线程就不会错过条件的任何变化。 pthread_cond_wait 返回时，互斥量再次被锁住。 


### pthread_cond_wait函数

首先假设日志队列为空，并且调度执行消费者线程A, 执行消费者代码：
```cpp
pthread_mutex_lock(&m_mtx); 
while(m_log.size() == 0)
{
	pthread_cond_wait(&m_notEmpty, &m_mtx); 
}
log = m_log.front(); 
m_log.pop_front(); 
pthread_cond_signal(&m_notFull);
// pthread_cond_broadcast(&m_notFull);  
pthread_mutex_unlock(&m_mtx); 
```
线程A, 进入临界区之前首先要获得并锁住互斥量，代码中 `pthread_mutex_lock(&m_mtx)`获得并锁住互斥量：m_mtx。 然后调用函数：
`pthread_mutex_lock(&m_notEmpty, &m_mtx);` 这个函数比较复杂，简单来说是这样的：
1. 线程A把锁住的互斥量 m_mtx 传给函数，函数然后自动把调用线程（线程A）放到等待条件的线程列表上。
2. 解开互斥锁 m_mtx, 把线程A投入睡眠。 
3. 条件变为真（其他线程调用了 pthread_cond_signal或pthread_cond_broadcast）, 线程A被唤醒，重新锁住互斥量，函数返回。 



### 生产者-消费者线程交互
（接上面）在线程 A, 在`2. 解开互斥锁 m_mtx, 把线程 A 投入睡眠。`后。 由于互斥锁现在已经被线程 A 释放， 假设现在有一个生产者线程 B, 它执行生产者代码：
```cpp
// 生产者线程核心代码
pthread_mutex_lock(&m_mtx); 
while(m_log.size() >= QUEUESIZE)
{
	pthread_cond_wait(&m_notFull, &m_mtx); 
}
m_log.push_back(log); 
pthread_cond_signal(&m_notEmpty); 
// pthread_cond_broadcast(&m_notEmpty); 
pthread_mutex_unlock(&m_mtx); 
```  

考虑下面的执行序列： 

1. 线程B, 进入临界区之前首先要获得并锁住互斥量，代码中 `pthread_mutex_lock(&m_mtx);` 获得并锁住互斥量： m_mtx。 然后执行: `while(m_log.size() >= QUEUESIZE)`， 条件不成立（因为此时队列为空）。 继续执行后面的代码： `m_log.push_back(log);` 向日志队列中插入了一条日志记录，然后通过调用：`pthread_cond_signal(&m_notEmpty);` 或者 `pthread_cond_broadcast(&m_notEmpty);` 把条件 m_notEmpty 置为真并唤醒在该条件等待列表上的线程A，线程 C（假设另一个消费者线程C此时也在 条件m_notEmpty的等待列表上）。
2. 线程B接着执行 `pthread_mutex_unlock(&m_mtx);` 解锁互斥量 m_mtx。 
3. 线程A和线程C争着去获得并锁住互斥量 m_mtx。
4. 假设线程 A 获得并锁住了互斥量 m_mtx, 线程 A 锁住互斥量 m_mtx后，从`pthread_cond_wait(&m_notEmpty, &m_mtx)` 返回。线程 C 由于没有获得互斥量，继续投入睡眠（在互斥量m_mtx的等待队列上）。  
5. 线程 A 执行`while(m_log.size() == 0)`, 条件不成立（此时日志队列中有一条日志记录，不为空）， 继续执行后面的代码`log = m_log.front(); m_log.pop_front(); `取出日志记录（日志队列又变为空）, 然后执行`pthread_cond_signal(&m_notFull);`或 `pthread_cond_broadcast(&m_notFull);` 来唤醒在条件变量m_notFull等待队列上的生产者线程，但是此时并没有生产者线程等待在条件变量m_notFull上（因为此时队列并没满），这个唤醒信号丢失，也就是被忽略。 然后继续执行 `pthread_mutex_unlock(&m_mtx);` 对互斥量解锁。 
6. 由于消费者线程 C 此时在 互斥量 m_mtx的等待队列上，假设消费者线程C此时获得并锁住了互斥量 m_mtx, 线程C 从`pthread_cond_wait(&m_notEmpty, &m_mtx)` 返回。
7. 线程 C 执行`while(m_log.size() == 0)`, 由于唯一的一条日志记录刚刚已经被消费者线程A取走，此时日志队列仍旧为空，消费者线程 C 执行`pthread_cond_wait(&m_notEmpty, &m_mtx);` 
8. `pthread_cond_wait(&m_notEmpty, &m_mtx);`函数的调用，把消费者线程 C 再次放到条件变量 m_notEmpty上，然后对互斥量 m_mtx解锁，把线程C投入睡眠。


上面的序列明白之后，其他的执行序列应该都能理解了。 

### 注意点
1. 在调用`pthread_cond_wait(&m_notEmpty, &m_mtx)` 函数的时候，**必须要确保线程调用该函数之前, 该线程已经锁住了互斥量 m_mtx。**. 
2. 一个条件变量只能被和一个互斥量进行绑定（即只能一个条件变量的条件，只能被一个互斥量进行保护）， 但是一个互斥量可以和多个条件变量绑定（即可以用一个互斥量保护多个条件变量的条件）。
3. `pthread_cond_wait(&m_notEmpty, &m_mtx);` 写在循环里面。(参考上面 消费者线程C获取并锁住互斥量以及其后序动作)。 

具体解释如下：
>
It is important to note that when pthread_cond_wait() and pthread_cond_timedwait() return without error, the associated predicate may still be false. Similarly, when pthread_cond_timedwait() returns with the timeout error, the associated predicate may be true due to an unavoidable race between the expiration of the timeout and the predicate state change. 

The application needs to recheck the predicate on any return because it cannot be sure there is another thread waiting on the thread to handle the signal, and if there is not then the signal is lost. The burden is on the application to check the predicate. 

Some implementations, particularly on a multi-processor, may sometimes cause multiple threads to wake up when the condition variable is signaled simultaneously on different processors. 

In general, whenever a condition wait returns, the thread has to re-evaluate the predicate associated with the condition wait to determine whether it can safely proceed, should wait again, or should declare a timeout. A return from the wait does not imply that the associated predicate is either true or false. 

It is thus recommended that a condition wait be enclosed in the equivalent of a "while loop" that checks the predicate. 


4. `pthread_cond_signal()` VS `pthread_cond_broadcast()`: `pthread_cond_signal` 函数的作用是发送一个信号给另外一个正处于阻塞等待状态的线程, 使其脱离阻塞状态，继续执行。 如果没有线程处在阻塞状态，pthread_cond_signal也会成功返回。使用 `pthread_cond_signal` 一般不会有**惊群现象（spurous wakeup）**产生，它一般只给一个线程发信号。假如有多个线程正在阻塞等待着这个条件变量的话，那么是根据等待线程优先级的高低确定哪个线程接收到信号然后开始执行。如果各线程优先级相同，则根据等待时间的长短来确定哪个线程获得信号。但无论如何一个 pthread_cond_signal调用最多发送一次信号。**但是 pthread_cond_signal在多核处理器上可能会同时唤醒多个线程，当你只能让一个线程处理某个任务时，其他被唤醒的线程由于争不到互斥锁就继续睡眠。**

5. `pthread_cond_signal() 与 pthread_mutex_unlock()`的调用顺序问题： 
`pthread_cond_signal()`即可以放在`pthread_mutex_lock()`和`pthread_mutex_unlock()`之间，也可以放在`pthread_mutex_lock()`和`pthread_mutex_unlock()`之后，但是各有各的缺点。
```cpp
// 放之间
pthread_mutex_lock
xxxxxxx
pthread_cond_signal
pthread_mutex_unlock
```
**缺点：**在某种线程的实现中，会造成等待线程从内核中唤醒（由于cond_signal)然后又回到内核空间（因为cond_wait返回后会有原子加锁的行为），所以一来一回会有性能的问题。


从逻辑上来说，这种使用方法是完全正确的。但是在多线程环境中，这种使用方法可能是低效的。**posix1标准说，`pthread_cond_signal`与`pthread_cond_broadcast`无需考虑调用线程是否是mutex的拥有者，也就是说，可以在lock与unlock以外的区域调用。如果我们对调用行为不关心，那么请在lock区域之外调用吧。

这里举个例子：
我们假设系统中有线程1和线程2，他们都想获取mutex后处理共享数据，再释放mutex。请看这种序列：
1). 线程1获取mutex，在进行数据处理的时候，线程2也想获取mutex，但是此时被线程1所占用，线程2进入休眠，等待mutex被释放。
2). 线程1做完数据处理后，调用`pthread_cond_signal()`唤醒等待队列中某个线程，在本例中也就是线程2。线程1在调用`pthread_mutex_unlock()`前，因为系统调度的原因，线程2获取使用CPU的权利，那么它就想要开始处理数据，但是在开始处理之前，mutex必须被获取，很遗憾，线程1正在使用mutex，所以线程2被迫再次进入休眠。
3). 然后就是线程1执行`pthread_mutex_unlock()`后，线程2方能被再次唤醒。
从这里看，使用的效率是比较低的，如果在多线程环境中，这种情况频繁发生的话，是一件比较痛苦的事情。

但是在Linux Threads或者NPTL里面，就不会有这个问题，因为在Linux 线程中，有两个队列，分别是cond_wait队列和mutex_lock队列， cond_signal只是让线程从cond_wait队列移到mutex_lock队列，而不用返回到用户空间，不会有性能的损耗。
所以在Linux中推荐使用这种模式。
 
```cpp
// 放后面
pthread_mutex_lock
xxxxxxx
pthread_mutex_unlock
pthread_cond_signal
```
优点：不会出现之前说的那个潜在的性能损耗，因为在signal之前就已经释放锁了
缺点：如果unlock和signal之间，有个低优先级的线程正在mutex上等待的话，那么这个低优先级的线程就会抢占高优先级的线程（cond_wait的线程)，而这在上面的放中间的模式下是不会出现的。


所以，在Linux下最好pthread_cond_signal放中间，但从编程规则上说，其他两种都可以。

### 附：`pthread_cond_wait`和`pthread_cond_signal`的大致实现
```cpp
pthread_cond_wait(mutex, cond):
    value = cond->value; /* 1 */
    pthread_mutex_unlock(mutex); /* 2 */
    pthread_mutex_lock(cond->mutex); /* 10 */
    if (value == cond->value) { /* 11 */
        me->next_cond = cond->waiter;
        cond->waiter = me;
        pthread_mutex_unlock(cond->mutex);
        unable_to_run(me);
    } else
        pthread_mutex_unlock(cond->mutex); /* 12 */
    pthread_mutex_lock(mutex); /* 13 */


pthread_cond_signal(cond):
    pthread_mutex_lock(cond->mutex); /* 3 */
    cond->value++; /* 4 */
    if (cond->waiter) { /* 5 */
        sleeper = cond->waiter; /* 6 */
        cond->waiter = sleeper->next_cond; /* 7 */
        able_to_run(sleeper); /* 8 */
    }
    pthread_mutex_unlock(cond->mutex); /* 9 */
```

<br/>
<br/>

#### 参考资料
- [通用线程：POSIX线程详解](https://www.ibm.com/developerworks/cn/linux/thread/posix_thread3/index.html)
- [pthread_cond_wait 为什么需要传递 mutex 参数](https://www.zhihu.com/question/24116967)
- [pthread_cond_wait() man page](https://linux.die.net/man/3/pthread_cond_wait)
- [深入理解pthread_cond_wait, pthread_cond_signal](http://blog.csdn.net/yeyuangen/article/details/37593533)
- [pthread_cond_signal() and pthread_cond_broadcast man page](https://linux.die.net/man/3/pthread_cond_signal)
- 《Unix环境高级编程》（APUE）

<br/>