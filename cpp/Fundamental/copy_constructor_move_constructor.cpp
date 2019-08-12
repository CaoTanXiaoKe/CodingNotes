#include <iostream>
using namespace std;

/*
*  g++ copy_constructor_move_constructor.cpp -std=c++11 -fno-elide-constructors
*/

class A{
public:
    int val;
    
    // 构造函数
    A(int x) : val(x) {
        cout << "Constructor" << endl;
    }

    // 拷贝构造函数
    A(const A& rhs) : val(rhs.val) {
        cout << "Copy Constructor" << endl; 
    }

    // 拷贝赋值函数
    A& operator=(const A& rhs) {
        val = rhs.val;
        cout << "Copy Assigment Operator" << endl;
        return *this;
    }

    // 移动构造函数
    A(const A&& rhs) : val(rhs.val) {
        cout << "Move Constructor" << endl; 
    }
};


A GetA() {
    return A(1);
}

A&& MoveA() {
    return A(1);
}

int main() {
    cout << "************ 1 **************" << endl;
    A a(1);     // 构造函数
    cout << "************ 2 **************" << endl;
    A b = a;    // 拷贝构造
    cout << "************ 3 **************" << endl;
    A c(a);     // 拷贝构造
    cout << "************ 4 **************" << endl;
    b = a;      // 拷贝赋值
    cout << "************ 5 **************" << endl;
    A d = A(1); // 构造一个临时对象，然后移动构造
    cout << "************ 6 **************" << endl;
    A e = std::move(a); // 移动构造
    cout << "************ 7 **************" << endl;
    A f = GetA();   // 构造一个临时变量A(1), 然后移动构造一个临时变量；然后用临时变量移动构造f。
    cout << "************ 8 **************" << endl;
    A&& g = MoveA();
    cout << "************ 9 **************" << endl;
    d = A(1);   // 构造一个临时变量A(1), 然后用该临时变量移动赋值d

    return 0;
}

