### 基本概念

在golang的调度中，有三个关键概念: P, M, G。 

- P 虚拟核心数( Logical Processor, hardware Threading number)，可以在程序中用`runtime.NumCPU()`获得。
- M 核心线程(OS Thread)，M是machine的缩写。每个P都和一个M绑定，反之不成立。 M用于管理Goruntine。
- G Go协程(Goroutine), Goroutine就是程序代码的逻辑流，类比于核心线程被OS调度，进行内核态和用户态的上下文切换，从核心上换入换出; goroutine被M调度，在M上上下文切换及换入换出。 

![img](/Users/wikichen/Desktop/MyProject/CodingNotes/Go/images/94_figure2.png)



Go的调度器里有两种存放就绪goroutine的队列，一种是全局队列Global Run Queue(GRQ), 一种是本地队列

### 参考资料

- [Go调度详解](https://zhuanlan.zhihu.com/p/27056944)
- [Scheduling In Go](https://www.ardanlabs.com/blog/2018/08/scheduling-in-go-part1.html)
- [Achieving concurrency in Go](https://medium.com/rungo/achieving-concurrency-in-go-3f84cbf870ca)

