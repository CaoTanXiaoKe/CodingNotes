## 重载的基本概念

**除了重载的函数调用运算符 operator() 之外， 其他重载运算符不能含有默认实参。** 

> 对于一个运算符函数来说， 它或者是类的成员，或者至少含有一个类类型的参数。 

因为重载运算符相当于相应的函数调用，所以某些指定了运算对象求值顺序无法保留下来。 
特别是： 逻辑与，或运算符， 逗号运算符。 
另外，C++语言已经定义了逗号运算符和取地址运算符作用于类的含义，所以一般情况下也不建议重载。 

> **Best Practices: 通常情况下，不应该重载逗号，取地址，逻辑与和逻辑或运算符。**

建议： 只有当操作的含义对于用户来说清晰明了时才使用运算符。如果用户对运算符可能有几种不同的理解，则使用这样的运算符将产生二义性。 

#### 选择作为成员或者非成员的准则

- 赋值(=)， 下表([])，调用(()) 和成员访问箭头(->)运算符必须是成员。 

- 复合赋值运算符一般来说应该是成员，但并非必须，这一点与赋值运算符略有不同。 

- 改变对象状态的运算符或者与给定类型密切相关的运算符，如递增，递减和解引用运算符，通常应该是成员。 

- 具有对称性的运算符可以转换任意一端的运算对象，例如算术，相等性，关系和位运算符等。因此它们通常应该是普通的非成员函数。 


