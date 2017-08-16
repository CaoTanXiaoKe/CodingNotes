1. 尽量把构造函数声明为： explicit

2. copy构造函数是一个尤其重要的函数，因为它定义了一个对象 如何 passed by value(以值传递)。 以 by value 传递用户自定义类型通常是个坏主意， pass-by-reference-to-const 往往是比较好的选择。

3. C++ 是个语言联邦： C, object-oriented C++, Template C++, STL。 

4. 对于单纯常量，最好以 const 对象或 enums 替换 #defines 对于形似函数的宏(macros), 最好改用 inline 函数替换 #defines. 

5. const int* ptr 与 int const * ptr 含义相同。声明迭代器 const 就像声明指针为 const 一样， 表示这个迭代器不得指向不同的东西。但它指向的东西的值是可以改动的。 如果你希望迭代器所指的东西不可被改动， 你需要的是 const_iterator. 

6. C++ 规定，对象的成员变量的初始化动作发生在进入构造函数本体之前。 构造函数最好使用成员初值列表（member initialization list），而不要在构造函数本体内使用赋值操作(assignment). 初值列表列出的成员变量，其排列次序应该和它们在class中声明次序相同。 

7. 问题：如果某编译单元内的某个 non-local static 对象的初始化动作使用了另一编译单元内的某个non-local static 对象， 它所用到的这个对象可能尚未被初始化，这是因为 C++ 对“定义于不同编译单元内的 non-local static 对象” 的初始化次序并无明确定义。 
解决方法： 将每一个 non-local static 对象搬到自己的专属函数内（该对象在此函数内被声明为 static）, 这些函数返回一个 reference 指向它所含的对象。 
	这个手法的基础在于： C++ 保证， 函数内的 local static 对象会在“该函数被调用期间” “首次遇上该对象之定义式”时被初始化。 所以如果你以“函数调用”(返回一个reference指向local static 对象)替换“直接访问 non-local static对象”， 你就获得了保证，保证你所获得的那个reference将指向一个历经初始化的对象。更妙的是，如果你从未调用 non-loacl static 的“仿真函数”，就绝不会引发构造和析构成本。 

8. 如果你自己没声明，编译器就会为类声明（编译器版本的）一个 copy构造函数，一个 copy assignment操作符和一个析构函数。此外如果你没有声明任何构造函数，编译器也会为你声明一个default 构造函数。所有这些函数都是 public 且 inline.  

9. 为驳回编译器自动（暗自）提供的机能，可将相应的成员函数声明为 private 并且不予实现。 使用像 Uncopyable 这样的 base class 也是一种做法。 另外 C++11 提供了 delete 用于指定某函数是删除的。 

10. polymorphic(带多态性质的) base class 应该声明一个 virtual 析构函数。 如果 class 带有任何 virtual 函数， 它就应该拥有一个 virtual 析构函数。 

11. class 的设计目的如果不是作为 base classes 使用，或不是为了具备多态性（polymorphically）就不应该声明 virtual 析构函数。 

12. 析构函数绝对不要吐出异常。 如果一个被析构函数调用的函数可能抛出异常，析构函数应该捕获任何异常，然后吞下它们（不传播）或结束程序。 

13. 如果客户需要对某个操作函数运行期间抛出的异常做出反应，那么 class 应该提供一个普通函数（而非在析构函数中）执行该操作。 

14. 确定你的构造函数和析构函数都没有（在对象被创建和被销毁期间）调用 virtual 函数，而它们调用的所有函数也都服从同一约束。 
在构造和析构期间不要调用 virtual 函数， 因为这类调用从不下降至 derived class(比起当前执行构造函数和析构函数的那层)。 

15. 令赋值（assignment）操作符返回一个 reference to *this. 

16. 确保当对象自我赋值时 operator= 有良好行为。 其中技术包括 比较“来源对象”和“目标对象”的地址，精心周到的语句顺序， 以及 copy-and-swap. 
确定任何函数如果操作一个以上的对象，而其中多个对象是同一对象时，其行为仍然正确。 

17. Copying 函数应该确保复制“对象内的所有成员变量”及“所有 base class成分”。 

18. 不要尝试以某个 copying函数实现另一个 copying函数。 应该将共同机能放进第三个函数中，并由两个 copying函数共同调用。 

19. 所谓资源就是，一旦用了它，将来必须还给系统。如果不这样，糟糕的事情就会发生。内存是我们必须管理的众多资源之一。其他常见的资源还包括文件描述符（file descriptors）, 互斥锁（mutex locks）, 图形界面中的字形和笔刷，数据库连接，以及网络 sockets. 

20. 为防止资源泄露，请使用 RAII(Resource Acquisition Is Initialization)对象，它们在构造函数中获得资源并在析构函数中释放资源。 两个常被使用的RAII classes 分别是 shared_ptr 和 auto_ptr. 前者通常是较佳选择，因为其 copy 行为比较直观。若选择 auto_ptr, 复制动作会使它（被复制物）指向 NULL. 

21. 复制 RAII 对象必须一并复制它所管理的资源，所以资源的copying行为决定RAII对象的copying行为。 
	普遍而常见的 RAII class copying 行为是： 抑制 copying, 施行引用计数法（reference counting）. 不过其他行为也都可能被实现。 

22. APIs 往往要求访问原始资源（raw resources）, 所以每一个 RAII class 应该提供一个“取得其所管理的资源”的办法。对原始资源的访问可能经由显式转换或隐式转换。 一般而言显式转换比较安全，但隐式转换对客户比较方便。 

23. 如果你在 new 表达式中使用 [], 必须在相应的 delete 表达式中也使用 []. 如果你在 new 表达式中不使用 [], 一定不要在相应的 delete 表达式中使用 []. 

24. 以独立语句将 newed对象存储于（置入）智能指针内。如果不这样做，一旦异常被抛出，有可能导致难以察觉的资源泄露。

25. 好的接口很容易被正确使用，不容易被误用。你应该在你的所有接口中努力达成这些性质。 “促进正确使用”的办法包括建立新类型，限制类型上的操作，束缚对象值，以及消除客户的资源管理责任。 shared_ptr 支持定制型删除器（custom deleter）。这可防范DLL问题，可被用来自动解除互斥锁等等。 任何接口如果要求客户必须记得做某些事情，就是有着“不正确使用”的倾向。

26. 一般而言，你可以合理假设“pass-by-value并不昂贵”的唯一对象就是内置类型和 STL 的迭代器和函数对象。至于其他任何东西都请遵守本条款的忠告，尽量以 past-by-reference-to-const 替换 pass-by-value。 尽量以 pass-by-reference-to-const 替换 pass-by-value。前者通常比较高效，并可避免切割问题（slicing problem）。 以上规则并不适用于内置类型，以及 STL 的迭代器和函数对象。对它们而言， pass-by-value 比较适当。

27. 绝不要返回 pointer 或 reference 指向一个 local stack 对象， 或返回 reference 指向一个 heap-allocated 对象， 或返回 pointer 或 reference 指向一个 local static 对象而有可能同时需要多个这样的对象。 

28. 宁可拿 non-member non-friend 函数替换 member 函数。 这样做可以增加封装性，包裹弹性，和机能扩充性。 

29. 如果你需要为某个函数的所有参数（包括被 this 指针所指的那个隐喻参数）进行类型转换，那么这个函数必须是个 non-member。  


#### OPP

25. “public继承”意味着 is-a 关系。 适用于 base classes 身上的每一件事情也一定适用于 derived classes身上，因为每一个 derived class 对象都是一个 base class 对象。 另两个常见的关系是： has-a 关系 和
is-implemented-in-terms-of(根据某物实现出)。 

26. derived classes 内的名称会遮掩 base classes 内的名称。在 public 继承下从来没有人希望如此。 为了让被遮掩的名称再见天日，可使用 using 声明式或转交函数（forwarding functions）. 

27. pure virtual 函数有两个最突出的特性：它们必须被任何“继承了它们”的具体 class 重新声明，而且它们在抽象 class 中通常没有定义。 但也可以给出一份实现，这种方法常用来分离接口和它的实现。 

28. 接口继承和实现继承不同。 在 public 继承下，derived classes 总是继承 base class 的接口。 
	pure virtual 函数只具体指定接口继承。 
	简朴的(非纯) impure virtual 函数具体指定接口继承及缺省实现继承。 
	non-virtual 函数具体指定接口继承以及强制性实现继承。 

29. virtual 函数的替代方案包括 NVI 手法及 Strategy 设计模式的多种形式。 NVI手法自身是一种特殊形式的 Template Method 设计模式。 
- 使用 non-virtual interface(NVI) 手法， 那是 Template Method 设计模式的一种特殊形式。 它以 public non-virtual 成员函数包裹较低访问性（pirvate 或 projected）的 virtual 函数。 
- 将 virtual 函数替换为 “函数指针成员变量”，这是 Strategy 设计模式的一种分解表现形式。 
- 以 function 成员变量替换 virtual 函数，因而允许使用任何可调用物(callable entity)搭配一个兼容于需求的签名式。 这也是 Strategy设计模式的某种形式。 
- 将继承体系内的 virtual 函数替换为另一个继承体系另一个继承体系内的 virtual 函数。 这是 Strategy 设计模式的传统实现手法。 
	将机能从成员函数移到 class 外部函数， 带来的一个缺点是，非成员函数无法访问 class 的 non-public 成员。 
	function对象的行为就像一般的函数指针。这样的对象可接纳“与给定的目标签名式（target signature）兼容”的所有可调用物（callable entities）。 

30. 绝对不要重新定义继承而来的 non-virstual 函数。 

31. virtual函数是动态绑定（dynamically bound），而缺省参数值却是静态绑定（statically bound）。
	绝对不要重新定义一个继承而来的缺省参数值，因为缺省参数值都是静态绑定，而 virtual 函数 --- 你唯一应该覆写的东西 --- 却是动态绑定。 

32. 复合（composition）的意义和 public 继承完全不同。
	在应用域（application domain），复合意味 has-a(有一个)。 在实现域（implementation domain），复合意味 is-implemented-in-terms-of(根据某物实现出)。 

33. 如果 classes 之间的继承关系是 private, 编译器不会自动将一个 derived class 对象（例如 student）转换为一个 base class对象（例如Person）。这和 public继承的情况不同。第二条规则是，由 private base class 继承而来的所有成员，在derived class 中都会变成 private 属性，纵使它们在 base class中原本是 protected或public属性。 

34. private继承意味 is-implemented-in-terms-of（根据某物实现出）。 它通常比复合（composition）的级别低。但是当derived class 需要访问 protected base class 的成员,或需要重新定义继承而来的 virtual函数时，这么设计是合理的。
	和复合（composition）不同， private继承可以造成 empty base 最优化。 这对致力于“对象尺寸最小化”的程序库开发者而言，可能很重要。 

35. C++的名称查找先于类型匹配，这一点正是 重载和覆盖（重写）的重要区别， 另外一个对多重继承和重载函数调用很重要的规则是： 在看到是否有个函数可取用之前， C++首先确认这个函数对此调用而言是最佳匹配。找到最佳匹配函数后，才检验其可用性。

36. 多重继承比单一继承复杂。它可能导致新的歧义性，以及对virtual继承的需要。
- virtual 继承会增加大小，速度，初始化（及赋值）复杂度等等成本。如果 virtual base classes不带任何数据，将是最具价值的情况。 
- 多重继承的确有正当用途。其中一个情况涉及“public 继承某个 Interface class” 和 “private 继承某个协助实现的class”的两相组合。 


##### 杂项

Boost的shared_ptr是原始指针(raw pointer)的两倍大, shared_ptr的计数器以及“删除器专属数据”是存放在动态内存中的，并且以 virtual 形式调用删除器，并在多线程程序修改引用次数时蒙受线程同步化（thread synchronization）的额外开销。但是只要定义一个预处理符号就可以关闭多线程支持。 
