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
a 的值竟然与和它比较的值有关， 逆天了！！！

应该成：
```cpp
template<typename T>
inline void callWithMax(const T& a, const T& b)
{
	return (a > b ? a:b); 
}
```