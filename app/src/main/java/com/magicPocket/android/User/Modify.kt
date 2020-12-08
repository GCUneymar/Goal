package com.sunnyweather.android.User

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.magicPocket.android.HomePage
import com.magicPocket.android.database.UserDAO
import com.sunnyweather.android.R
import kotlinx.android.synthetic.main.activity_modify.*

//修改密码页面的逻辑代码

class Modify: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify)

        val set: Button = findViewById(R.id.btn_modify)


        //先判断原密码是否正确
        //然后判断两次输入的密码是否一致
        //根据用户名,修改密码
        set.setOnClickListener {
            val prepass = previous_password.text.toString()
            val newpass = new_password.text.toString()
            val surepass = sure_password.text.toString()
           Log.d("wdnmd","原来的密码${prepass}")
            Log.d("wdnmd","新的密码${newpass}")
            Log.d("wdnmd","新新的密码${surepass}")
            if(prepass==""|| newpass=="" ||surepass==""){
                Toast.makeText(this,"请输入所有信息",Toast.LENGTH_SHORT).show()
            }
            if(prepass != UserInfo.password){
                Toast.makeText(this,"原密码错误~~ 请重新输入",Toast.LENGTH_SHORT).show()
            }
            if(newpass== UserInfo.password) {
                Toast.makeText(this,"新密码与原密码一致，请重新输入",Toast.LENGTH_SHORT).show()

            }
            if((newpass!=surepass)&&(newpass!="")&&(surepass!="")){
                Toast.makeText(this,"两次输入的密码不一致 请重新输入",Toast.LENGTH_SHORT).show()
            }
            if((prepass== UserInfo.password)&&(newpass!="")&&(surepass!="")&&(newpass==surepass)){
                UserDAO.modifyUser(UserInfo.username,newpass)
                Toast.makeText(this,"密码修改成功！！！",Toast.LENGTH_SHORT).show()
                val intent =   Intent(this, HomePage::class.java)
                startActivity(intent)

            }

        }

    }
}