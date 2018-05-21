package com.maeun.random_group_chatting

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class Adapter(var item : ArrayList<Message>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mainView : View = LayoutInflater.from(parent.context)
                .inflate(R.layout.chat, parent, false)
        return ViewHolder(mainView)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.chat.text = item[position].chat
    }

}