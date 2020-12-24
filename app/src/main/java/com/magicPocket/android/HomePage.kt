package com.magicPocket.android


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.magicPocket.android.joke.JokeActivity
import com.sunnyweather.android.R
import com.sunnyweather.android.User.Modify

//APP的主界面
class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val calculator: Button =findViewById(R.id.btn_calculator)
        val weather: Button =findViewById(R.id.btn_weather)
        val set: Button =findViewById(R.id.btn_set)
        val express: Button =findViewById(R.id.btn_express)
        val flashlight: Button =findViewById(R.id.btn_flashlight)
        val funnyjoke:Button = findViewById(R.id.btn_joke)
        //计算器
        calculator.setOnClickListener {
            val intent  = Intent(this,CalActivity::class.java)
            startActivity(intent)
        }

        //天气预报
        weather.setOnClickListener {
            val intent = Intent(this, Main2::class.java)
            startActivity(intent)

        }

        //设置  → → 【修改用户的密码】
        set.setOnClickListener {
            val intent =    Intent(this, Modify::class.java)
            startActivity(intent)
        }

        //查询快递
        express.setOnClickListener {
            val intent = Intent()

            intent.action = "android.intent.action.VIEW"

            val content_url: Uri = Uri.parse("https://m.kuaidi100.com/")

            intent.data = content_url

            startActivity(intent)
        }

        //查看笑话
        funnyjoke.setOnClickListener{
            val intent = Intent(this, JokeActivity::class.java)
            startActivity(intent)

        }

        //实验闪光灯 is 单数点击开启，偶数关闭
        //当闪光灯开启的时候  更换按钮的颜色
        //当闪光灯关闭时 恢复按钮的颜色【先搞点主要得拓展】
        var isOn=true
        FlashLight.init(this)
        flashlight.setOnClickListener {

            if (isOn) {

                FlashLight.open()
                isOn=false
                Toast.makeText(this, "打开了手电筒", Toast.LENGTH_SHORT).show()
            } else {
                FlashLight.close()
                isOn=true
                Toast.makeText(this, "关闭了手电筒", Toast.LENGTH_SHORT).show()
            }
        }

    }

}