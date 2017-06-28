package com.example.admin.lfutilapp.kotlin_demo

/**
 * Created by admin on 2017/6/28.
 */
enum class Color{
    RED,BLACK,BLUE,GREEN,WHITE
}
fun display(){
    var color:Color=Color.BLACK
    // 转换指定name为枚举值，若未匹配成功，会抛出IllegalArgumentException
    println(color.name)////获取枚举名称
    println(color.ordinal)//获取枚举值在所有枚举数组中定义的顺序,0开始
}
fun main(args:Array<String>){
    display()
}