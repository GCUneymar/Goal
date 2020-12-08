package com.magicPocket.android.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name:String, version:Int) :
    SQLiteOpenHelper(context, name, null, version){
    private val createUser ="create table User (" +
            "username String primary key,"+
            "password String)"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createUser)
        Toast.makeText(context,"Create succeed",Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}