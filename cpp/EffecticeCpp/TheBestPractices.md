## The Best Practices

#### 01: View C++ as a federation of languages.  
视 C++ 为一个语言联邦。 分别从不同的视角看待C++有助于理解这种强大（开挂）到恐怖的语言。 
C++ 是个多重范型编程语言(multiparadigm programming language), 一种同时支持过程形式（procedural）,面向对象形式(object-oriented), 函数形式(functional)，范型形式(generic), 元编程形式(metaprogramming)的语言。 

把 C++ 看做四种子语言
- C
- Object-Oriented C++
- Template C++
- STL


**C++高效编程守则视状况而变化，取决于你使用C++ 的哪一部分。**


#### 02: Prefer consts, enums, and inlines to #defines. 
尽量以 const, enum, inline 替换 #define

- **对于单纯常量，最好以 const 对象或enums替换#defines.**
- **对于形似函数的宏(macros),最好改用inline函数替换 #defines.**
例如：
```cpp
#define CALL_WITH_MAX(a, b) f((a) > (b) ? (a) : (b))
```
会出现不可思议的地方：
```cpp
int a = 5, b = 0; 
CALL_WITH_MAX(++a, b);		// a 被累加两次（因为被访问了两次）
CALL_WITH_MAX(++a, b+10);	// a 被累加一次（因为被访问了一次）
```
a 的值竟然与和它比较的值有关， 卧槽逆天了！！！

应该成：
```cpp
template<typename T>
inline void callWithMax(const T& a, const T& b)
{
	return (a > b ? a:b); 
}
```
#### 03: Use const whenever possible. 
尽可能使用 const

STL迭代器系以指针为根据塑模出来，所以迭代器的作用就像个 T* 指针。声明迭代器为const就像是声明指针为 const 一样（即：声明一个 T* const 指针），表示这个迭代器不得指向不同的东西，但是它指向东西的值是可以改变的。 如果你希望迭代器所指的东西不可被改动（即希望STL模拟一个 const T*指针），你需要的是 const_iterator. 

- 将某些东西声明为 const 可帮助编译器侦测出错误用法。 const 可被施加于任何作用域内的对象，函数参数，函数返回类型，成员函数本体。
- 编译器强制实施 bitwise constness, 但你编写程序时应该使用“概念上的常量性”（conceptual constness）. 
- **当 const 和 non-const 成员函数有着实质等价的实现时，令 non-const 版本调用 const 版本可避免代码重复。** 
例如:
```cpp
class TextBlock{
public:
 	...
  	const char& operator[](std::size_t position) const	
  	{
  		...
		...
		return text[position];
  	}
  	char& operator[](std::size_t position)
  	{
  		return
  			const_cast<char&>(
  					static_cast<const TextBlock&>(*this)[position]
  					);
  	}
  	...
}; 

```
而用 const 版本调用 non-const 版本以避免重复----并不是个好选择，因为 const 成员函数承诺绝不改变其对象的逻辑状态，但是它却可能被 non-const 函数改变了。 