package com.example.admin.lfutilapp.kotlin_demo

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.admin.lfutilapp.R

class KotlinActivity : AppCompatActivity() {
    /**
     * 扩展函数
     */
    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        toast("")
    }
}
