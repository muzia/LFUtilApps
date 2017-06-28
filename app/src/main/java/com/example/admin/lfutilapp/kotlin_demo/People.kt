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
    }
}