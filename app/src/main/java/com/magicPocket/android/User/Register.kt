package com.sunnyweather.android.User


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.magicPocket.android.database.UserDAO
import com.magicPocket.android.MainActivity
import com.sunnyweather.android.R
import kotlinx.android.synthetic.main.activity_register.*

//注册页面的逻辑代码

class Register: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val register: Button = findViewById(R.id.btn_register)

        register.setOnClickListener {
            val username = login_name.text.toString()
            val password = login_password.text.toString()
            val surepass = sure_password.text.toString()
            if(UserDAO.findUser(username)==0) {
                Toast.makeText(this, "用户已存在", Toast.LENGTH_SHORT).show()
                login_name.setText("")
                login_password.setText("")
                sure_password.setText("")
            }

            else if(username!=""&&(password!= "")&&(surepass!="")&&(password==surepass)){
                    UserDAO.addUser(username, password)
                    println("Register--line--37")

                    val intent = Intent(this, MainActivity::class.java)

                    //等待数据写入数据库,时间设置2s
                    try {
                        Toast.makeText(this, "注册成功,即将返回登录页面", Toast.LENGTH_LONG).show()
                        Thread.sleep(2000)
                    } catch (e: Exception) {
                        println("绝对不会出错~~")
                    }

                    startActivity(intent)
                }
            if(username ==""||password==""||surepass==""){
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show()
                login_name.setText("")
                login_password.setText("")
                sure_password.setText("")
            }
            if((password== null)||(surepass==null)||(password!=surepass)){
                Toast.makeText(this, "用户名或密码不能为空,且两次密码需一致", Toast.LENGTH_SHORT).show()
                login_name.setText("")
                login_password.setText("")
                sure_password.setText("")
            }

            println(username+"------------------"+password)
        }

    }


}