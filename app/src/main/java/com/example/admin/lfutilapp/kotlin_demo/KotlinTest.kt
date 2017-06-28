package com.example.admin.lfutilapp.kotlin_demo

import org.jetbrains.annotations.NotNull

/**
 * Created by admin on 2017/6/22.
 */
//
var str:String=""
var str1=""
val iArray: IntArray = intArrayOf(1, 2, 3)
val sArray: Array<String> = Array<String>(3, { i -> i.toString() })
val anyArray: Array<Any> = arrayOf(1, "2", 3.0, 4.1f) // 可将类型进行混排放入同一个数组中
val lArray: LongArray = longArrayOf(1L, 2L, 3L)
fun main(args:Array<String>){
    str="Hello world!"
    println(str)
    println(sum(1,2))
    println(max1(1,2))
    printInt(2)
    //倒序输出5 4 3 2 1 0
    for (i in 5 downTo 0) {
        println(i)
    }

    loop@ for (i in 0..2) {
        for (j in 0..3) {
            println("i:" + i + "  j:" + j)
            if (j == 2)
//            continue@loop//跳到外层循环，继续往下执行
                break@loop  //跳到外层循环label处，跳出改层循环
        }
    }
    //设置输出数据步长
    for (i in 1..5 step 3) println(i) // 输出 1  4
    //step和downTo混合使用
    for (i in 5 downTo 1 step 3) println(i) //输出5  2
    var test: String?= null
}
//表达式可以作为函数体
fun sum(a:Int,b:Int):Int=a+b
fun max1(a:Int,b: Int):Int=if (a>b) a else b
//unit==void 可省略
fun printInt(a: Int) :Unit{
    println(a)
}
//循环
fun forloop(array:Array<String>){
    for(str in array){
        print(str)
    }
    array.forEach { print(it) }
    for (i in array.indices){
        print(array[i])
    }
    var i=0
    while (i<array.size){
        print(array[i])
    }
}
//使用when判断类型
fun whenFun(obj:Any){
    when(obj){
        25  -> print("25")
        "kotlin"-> print("kotlin")
        //is 等同于instanseof
        !is String -> print("not string")
        else -> print("nothing")
    }
}




