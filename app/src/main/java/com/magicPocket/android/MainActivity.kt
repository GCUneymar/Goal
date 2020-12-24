package com.magicPocket.android

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import com.sunnyweather.android.R
import com.sunnyweather.android.User.Register
import com.sunnyweather.android.User.UserInfo
import com.magicPocket.android.database.MyDatabaseHelper
import com.magicPocket.android.database.UserDAO
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {

    val dbHelper = MyDatabaseHelper(this,"UserData.db", 1)
    var db: SQLiteDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (SunnyWeatherApplication.TOKEN.isEmpty()) {
            Toast.makeText(this, "请在SunnyWeatherApplication中填入令牌值", Toast.LENGTH_LONG).show()
            finish()
        }

        db = dbHelper.writableDatabase
        UserDAO.db=dbHelper.writableDatabase
        val login: Button = findViewById(R.id.btn_login)
        val register: Button = findViewById(R.id.btn_register)


        login.setOnClickListener {
            val username:String = et_login_name.text.toString()
            val password = et_login_password.text.toString()

            if(username==""||password==""){
                Toast.makeText(this, "用户名或密码不能为空!!!!", Toast.LENGTH_SHORT).show()
                et_login_name.setText("")
                et_login_password.setText("")
            }

            else if(UserDAO.loginUser(username,password)!=0){
                val intent =   Intent(this, HomePage::class.java)

                //设置用户名和密码
                // 用于用户修改密码功能
                UserInfo.username=username
                UserInfo.password=password
                startActivity(intent)
                Toast.makeText(this, "登录成功，欢迎 ${username}!!!!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "密码错误!!!!", Toast.LENGTH_SHORT).show()
                et_login_name.setText("")
                et_login_password.setText("")
            }


        }

        register.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }
}
