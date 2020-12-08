package com.magicPocket.android.joke

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sunnyweather.android.R
import kotlinx.android.synthetic.main.joke_list.*

class JokeActivity : AppCompatActivity() {
    private val jokeList = ArrayList<Joke>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.joke_list)
        initFruits()
        val adapter = JokeAdapter(this, R.layout.joke_item, jokeList)
        listView.adapter = adapter

        listView.setOnItemClickListener {
                parent, view, position, id -> val jokes = jokeList[position]
            Log.d("笑话的名字///",jokes.name)//对position取模10
            val jokeID :Int = position % 10
            val jokeName :String = jokes.name
            val intent =    Intent(this, JokeDetails::class.java)
            intent.putExtra("jokeName",jokeName)
            intent.putExtra("jokeID",jokeID)
            startActivity(intent)
        }

    }
    private fun initFruits() {
        repeat(2) {
            jokeList.add(Joke("走夜路",R.drawable.hahaha))
            jokeList.add(Joke("打农药",R.drawable.hahaha))
            jokeList.add(Joke("双十一不剁手了",R.drawable.hahaha))
            jokeList.add(Joke("自尊",R.drawable.hahaha))
            jokeList.add(Joke("老实人",R.drawable.hahaha))
            jokeList.add(Joke("结婚",R.drawable.hahaha))
            jokeList.add(Joke("讲故事",R.drawable.hahaha))
            jokeList.add(Joke("海底捞",R.drawable.hahaha))
            jokeList.add(Joke("暗恋",R.drawable.hahaha))
            jokeList.add(Joke("安全感",R.drawable.hahaha))
        }
    }
}