package com.magicPocket.android.database

import android.database.Cursor
import com.magicPocket.android.MainActivity
import java.lang.Exception

object UserDAO{

    var db = MainActivity().db
    var cursor: Cursor? = null
    //用户注册 注册成功返回1，注册失败返回0
    fun addUser(username:String, password:String) :Int{

        db?.beginTransaction()
        try {
            println("正在注册~~~~~")
            println(db)
            println("正在注册~~~~~")
            println(username)
            println(password)

            db?.execSQL("insert into User (username, password) values(?,?)", arrayOf(username,password))
            db?.setTransactionSuccessful()
        }catch (e:Exception){
            println("注册失败了~~~")
            e.printStackTrace()
            return 0

        } finally {
            db?.endTransaction()
        }

        return 1
    }

    //修改用户的密码 修改成功返回1，修改失败返回0
    fun modifyUser(username:String, password:String) :Int{
        db?.beginTransaction()
        try {
            db?.execSQL("update User set password =? where username = ?", arrayOf(password, username))
        }catch (e:Exception){
            return 0;
            e.printStackTrace()
        } finally {
            db?.endTransaction()
        }

        return 1
    }

    //注册时查询用户是否存在 用户名不存在返回1 用户名存在返回0
    fun  findUser(username:String) :Int{
        db?.beginTransaction()
        try {
            cursor = db?.rawQuery("select * from User where username =?", arrayOf(username))
        }catch (e:Exception){
            println("UserDAO---line--47--出错了")
            return 0

        } finally {
            if ((cursor == null)|| cursor?.moveToFirst()!!){
                return 1
            }
            db?.endTransaction()
        }
        return 1
    }

    //登录时查询用户是否存在 验证成功返回1，验证失败返回0
    fun  loginUser(username:String, password: String) :Int{
        db?.beginTransaction()
        try {
            if (db != null) {
                cursor = db?.rawQuery("select * from User where username =? and password=?", arrayOf(username, password),null)
            }
        }catch (e:Exception){
            e.printStackTrace()
            return 0
        } finally {
            if((cursor?.moveToFirst()!!)){
                return 1
            }
            db?.endTransaction()
        }
        return 0
    }

}