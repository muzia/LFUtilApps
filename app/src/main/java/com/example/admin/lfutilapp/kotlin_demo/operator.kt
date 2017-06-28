package com.example.admin.lfutilapp.kotlin_demo

/**
 * Created by admin on 2017/6/28.
 */
//操作符
val String.lastChar: Char
    get() = this[this.length - 1]

class A(val p: Int)
/*
//1,反射得到运行时的类引用:
val c = Student::class
//2,函数引用
fun isOdd(x: Int) = x % 2 != 0
val numbers = listOf(1, 2, 3)
println(numbers.filter(::isOdd))

//3，属性引用(在此引用main函数主体外声明的变量)
println(::x.get())
::x.set(2)
println(x)
//4，::x 表达式评估为 KProperty<Int> 类型的属性
//它允许我们使用 get() 读它的值或者使用名字取回它的属性
val prop = A::p
println(prop.get(A(1)))

//5，对于扩展属性
println(String::lastChar.get("abc"))

//6，与 java 反射调用
println(A::p.javaClass),
var f: Array<Field> = A::p.javaClass.declaredFields*/
