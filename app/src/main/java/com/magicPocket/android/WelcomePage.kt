package com.sunnyweather.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.magicPocket.android.MainActivity

//欢迎界面
//还没开始做
class WelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)
        try {
            Thread.sleep(2000)
        } catch (e: Exception) {
            println("不会被看见")
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}