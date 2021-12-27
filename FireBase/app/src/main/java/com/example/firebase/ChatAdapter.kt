package com.example.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val context:Context, var users:ArrayList<UserInfo>) : RecyclerView.Adapter<ChatAdapter.viewHolder>() {

    open class viewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        var userName: TextView =itemView.findViewById(R.id.userName)
        var lastmsg:TextView=itemView.findViewById(R.id.lastMessage)
        var timestamp:TextView=itemView.findViewById(R.id.timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.chat_layout,parent,false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val user= users[position]
        holder.userName.text=user.UserName
        holder.lastmsg.text=user.lastmsg
        holder.timestamp.text=user.timestamp.toString()

    }

    override fun getItemCount(): Int {
        return users.size
    }
}