package com.example.admin.lfutilapp.kotlin_demo

/**
 * Created by admin on 2017/6/28.
 */
/**
 * 类与枚举
 */
open class People private constructor(var id:String,var name:String){
    //初始化属性
    var CustomName=name.toUpperCase()
    constructor(id:String,name:String,age:String):this(id,name){
        println("constructor")
    }
    init {
        println("初始化操作，可使用constructor参数")
    }
    open fun study(){
        print("study")
    }
    class Student(id: String,name: String):People(id,name){
        var test:Number=3
        private var name1:String

            get() {
            return name1
        }
        set(value) {
            name1=value
        }

        override fun study() {
            super.study()
        }
        //,Kotlin的class并不支持static变量,所以需要使用companion object来声明static变量,
        // 其实这个platformStatic变量也不是真正的static变量,而是一个伴生对象,
        companion object {
            val ID = 1
        }
        /**
         * 实例化的时候，单例是懒加载，当使用的时候才去加载；而对象表达式是在初始化的地方去加载。
         *
         * 当在类内部使用 object 关键词定义对象时，允许直接通过外部类的类名访问*内部对象进
         *而访问其相关属性和方法，相当于静态变量
         * 可以使用companion修饰单例，则访问其属性或方法时，允许省略单例名
         * MyClass.doSomething() // 访问内部单例对象方法
         */
        class MyClass {
            companion object Singleton {
                fun doSomething() {
                    println("doSomething")
                }
            }
        }
    }
}