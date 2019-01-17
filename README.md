# concurrency

#### 项目介绍
并发编程

可见性-volatile
通过内存屏障和禁止重排序优化实现
1.对volatile变量写操时,会在写操作后加入一条store屏障指令,将本地内存中的共享变量值刷新到主内存
2.对volatile变量读操时,会在读操作后加入一条load屏障指令,从主内存中读取共享变量


有序性
Java内存模型中,,允许编译器和处理器对指令进行重排序,但是重排序过程不会影响到单线程的执行,
却会影响到多线程并发执行的正确性
*

##############
5.安全发布对象
发布对象->对一个对象能够被当前范围之外的代码所使用

对象逸出->一种错误的发布.当一个对象还没有构造完成时.就使他被其他线程所见

安全的发布对象的4种方法
*在静态初始化函数中初始化一个对象的引用
*将对象的引用保存到volatile类型域或者AtomicReference对象中
*将对象的引用保存到某个正确的构造对象的final类域中
*将对象的引用保存到一个由锁保护的域中


##########################
1.不可变对象
   满足条件
   a.创建对象后状态就不能修改
   b.对象的所有域都是final类型
   c.对象是正确创建的(在对象创建期间,this引用没有逸出)


##########################
如何实现线程封闭
* Ad-hoc线程封闭 :程序控制实现,最糟糕,忽略
* 堆栈封闭 :局部变量 ,无并发问题(方法类的变量)就是平时写的接口定义在方法中的变量
* ThreadLocal :特别好的封闭方法   

线程不安去全的类以及写法

字符串拼接  
stringBuilder   线程不安全
 
 stringBuffer  线程安全  
 
 
 simpleDateFormat   日期转换的类    线程不安全   (如果要使用就要实行堆栈封闭性写法->写在方法中 ,每次都new 一个新的)
 jodaTime  线程安全 推荐使用joda-time

List 线程不安全
HashSet  线程不安全
HashMap 线程不安全

线程安全 -同步容器

Java中同步容器 有哪些   ->性能不算很好 ,使用的场景比较少,一般使用并发容器
Arraylist ->vector,Stack
vector 他的方法是被synchnized修饰的 
stack 继承vector  先进后出

HashMap->HashTable(key value不能为null)

Collectors.synchronized(List,Set,Map)

注意 :使用 foreaach 和迭代器 便利的时候 不要去做删除


线程 安全 - 并发容器 JUC    ============================

JUC  包含 五部分
1.aotmic
2.locks
3.tools
4.executor
5.collections



ArrayList -> 
CopyOnWriteArrayList   线程安全
1.读写分离
2.最终一致性
3.使用时,另外开辟空间
记住:读操作时,都是字啊原数组上读的,不需要加锁,写操作时是加锁的

HashSet ,TreeSet -> 
CopyOnWriteArraySet ,ConcurrentSkipListSet 
他们的add()操作,remove()操作时线程安全的,因为只能保持add,以及 remove contain 是原子性操作,如果调用 removeAll(),containAll(),addAll()操作时,要额外加锁的

HashMap,TreeMap ->

ConcurrentHashMap,ConcurrentSkipListMap
ConcurrentHashMap,HashMap线程安全的版本,注意:要求不能有空值,针对读操作进行大量的优化 ,因此具有很大的高并发性
ConcurrentSkipListMap 是TreeMap线程安全的版本  key是有序的,支持更高的并发,线程数更多,更能体现更高的并发度


##################################
7.JUC之AQS

AbStractQueuedSynchronizer  -AQS
底层的是双向列表
简单介绍
1.使用node实现fifo队列,可以用与构建锁或者其他的同步装置的基础框架
2.利用一个int类型表示状态
3.使用方法继承
4.子类通过继承并通过实现它的方法管理其他的状态的方法操纵状态
5.可以同时实现排它锁和共享锁模式(独占,共享)

AQS 同步组件===============

CountDownLatch 它是闭锁
通过一个计数来保持线程是否需要一直阻塞

Semaphone 
能控制同一时间线程并发的数目
提供两个方法 
acquire();//获取一个许可
release();//释放一个许可



Cyclicbarrier 跟CountDownLatch一样也能阻塞线程,都是通过计数器来实现的
与CountDownlatch的区别
1.Cyclicbarrier的计数器可以重置
2.Cyclicbarrier多个线程等待


ReentrantLock 锁
java 一共两种锁  一种是synchronize锁 一种是 jUC中的锁

synchronize 与 ReentrantLock的区别
可重入性 ReentrantLock(可重入性)
锁的实现 snc是JVM
性能的的区别
功能区别   snc便利    lock 手动去加锁    ,== 细粒度 lock优于snc   

ReentrantLock的独有功能
1.可指定是公平还是非公平锁(snc只能是非公平锁)
2.提供一个condition类,可以分组唤醒需要唤醒的线程
3.提供能够中断等待锁的线程机制 lock.lockInterruptibly()

总结一下 锁的类
synchronized 是基于Jvm的,可以通过监控工具监控synchronize的锁定,出现异常时,会释放synchronized的锁定,
Jvm会自动做加锁,与解锁
ReentrantLock stampLock 是对象层面的锁定,要保证锁一定释放,一定要把锁放到finally中才会安全,stampLock对吞吐量有巨大的改进
特别是在读线程越来越多的情况下

###############
8.FutureTask
任务执行完之后可以拿到结果 ,

Callable与Runnable对比
Runnable是一个接口,只有一个run方法
Callable对线程执行后有返回值
Future 接口,他可以查询任务是否取消,以及获取结果.可以监视目标线程调用情况,调用 他的get()方法可以获取他的结果

FutureTask 最终执行Callable类型任务,如果构造函数是Runnable的类型他会转成Callable类型

fork/Join 
是java7提供的并行执行任务框架,他是一个把大任务,分割成若干个小任务,最终汇总每个2结果后,得到大任务结果的框架


BlockingQueue 阻塞对列=========
当一个线程对已经满了的对列进行操作,就会阻塞,除非有个线程出对列.线程安全的
提供四个方法 insert() remove(),examine()检查;
五种实现
ArrayBlockingQueue
DelayQueue
LinkedBlockingQueue
PriorityBlockingQueue
SynchronousQueue


############
9.线程调度->线程池
new Thread 弊端
1.每次new Thread 新建对象,性能差
2.线程缺乏统一的管理,可能无限制的新建线程,相互竞争,有可能占用过多的系统资源导致司机或OOM
3.缺少更多的功能,如更多的执行,定期的执行,线程中断

线程池好处
1.重用存在线程,减少对象创建,消亡的开销,性能佳
2.可有效控制最大并发线程数,提高系统资源利用效率,同事可以避免过多的资源竞争,避免阻塞
3.提供定时执行,定期执行,单线程,并发数控制等功能

ThreadPoolExecutor
1.corePoolSIze :核心线程数量
2.maximumPoolSize:线程最大线程
3.workQueue:阻塞对列,存储等待执行的任务,很重要,会对线程池运行过程产生重大影响
4.keepAliveTime:线程没有任务执行时最多保持多久时间终止
5.unit :keepAliveTime的时间单位
6.threadFactory:线程工厂,用来创建线程
7.rejectHandler:当拒绝处理任务时的策略

#######################
10,多线程并发扩展

10.1死锁发生必要条件
1.互斥条件
请求和保持条件
不剥夺条件
环路等待条件

10.2多线程并发最佳实践
1.使用本地变量
2.使用不可变类
3.最小化锁的作用分范围
4.使用线程池的Executor
5.宁可使用同步也不用使用线程的wait和 notify
6.使用并发集合而不是加了锁的同步集合.
7.使用blokingQueue实现生产-消费模式
8.使用Semamphore创建有界范围
9.宁可使用同步代码块,也不使用同步方法
10.避免使用静态变量

10.3spring与线程安全
spring bean: singleton,prototype
无状态对象
 
 10.4 HashMap与ConcurrentHashMap
hsahmap底层是数组

############3
11.高并发处理的思路




