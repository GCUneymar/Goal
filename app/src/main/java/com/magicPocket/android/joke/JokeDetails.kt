package com.magicPocket.android.joke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.sunnyweather.android.R

class JokeDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke_details)
        val jokeName = intent.getStringExtra("jokeName")
        val jokeID = intent.getIntExtra("jokeID",1)
        val jokesDetails: List<String> = listOf("一个人走夜路，周围特别黑，我又那么帅…好害怕，好怕别人看不到。",
            "早上我去菜市场买青菜，我问小贩：“你这菜打过农药吗？”小贩想了想说：“估计打不过。”",
            "刚发工资时：这个月我要管住自己，不买任何垃圾！第二天打开淘宝：野生葫芦娃？有多野生？买个试试！",
            "如果你给我的，和你给别人的是一样的，那我就不要了。食堂阿姨甩起大勺：“你到底要不要？要不要？”",
            "长得好看的人会被人莫名其妙的附加好多属性，聪明，善良，可爱……磕碜的人就一个词：踏实！",
            "小时候看到爸妈吵架，我就时常纠结，长大后该不该结婚？直到到了年龄之后，我才发现：我真的想太多！",
            "“老公，我明天早上起来做早饭给你吃吧！”“临睡前不要讲恐怖故事，快点睡！” ",
            "虽然我不能为你上九天揽月，但是我可以陪你下海底啊，捞肥牛，捞鱼丸，捞大虾，捞…什么都捞给你。",
            "“和暗恋的女生吵架，她很久没有理我了。”“那你去道歉啊！”“算了，已经十年了。”"
            ,"如果一个男生的手机壁纸是你，所有的社交密码都告诉你，那么你就取了他的钱走吧。")

        val textOne :TextView = findViewById(R.id.jokeTitle)
        val textTwo :TextView = findViewById(R.id.jokeDetails)
        textOne.text = jokeName
        textTwo.text = jokesDetails.get(jokeID)




    }
}