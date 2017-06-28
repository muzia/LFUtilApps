package com.example.admin.lfutilapp.kotlin_demo

/**
 * Created by admin on 2017/6/28.
 */
/**
 * 实体类
 */
data class Staff<T>(var name: String,  val position: String,var age:T)
fun main(args:kotlin.Array<String>){
var staff=Staff<String>("lf","0","24")
    System.out.println(staff.age)
}