package com.magicPocket.android.joke

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sunnyweather.android.R

class JokeAdapter (activity: Activity, val resourceId:Int, data:List<Joke>):ArrayAdapter<Joke>(activity,resourceId,data){
    inner class ViewHolder(val  bookImage: ImageView, val bookName: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view:View
        val viewHolder : ViewHolder
        if(convertView == null){
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val bookImage:ImageView = view.findViewById(R.id.bookImage)
            val bookName: TextView = view.findViewById(R.id.bookName)
            viewHolder = ViewHolder(bookImage, bookName)
            view.tag=viewHolder

        }else{
            view = convertView
            viewHolder=view.tag as ViewHolder
        }

        val joke = getItem(position)

        if(joke != null){
            viewHolder.bookImage.setImageResource(joke.imageId)
            viewHolder.bookName.text = joke.name
        }
        return view
    }
}