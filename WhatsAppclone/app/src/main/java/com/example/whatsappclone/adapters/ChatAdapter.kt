package com.example.whatsappclone.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.ChatDetailActivity
import com.example.whatsappclone.R
import com.example.whatsappclone.models.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ChatAdapter(private val context: Context, var users: ArrayList<UserInfo>) :
    RecyclerView.Adapter<ChatAdapter.viewHolder>() {

    open class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profilePic: ImageView = itemView.findViewById(R.id.profile)
        var userName: TextView = itemView.findViewById(R.id.userName)
        var lastmsg: TextView = itemView.findViewById(R.id.lastMessage)
        var timestamp: TextView = itemView.findViewById(R.id.timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.chat_layout, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val user = users[position]
        holder.userName.text = user.UserName
        //to upload image in online mode
        if(user.profilePic?.isEmpty() == false)
            Picasso.get().load(user.profilePic).into(holder.profilePic)

        //to delete chat (chat will be deleted for all users so that's not a good approach.
        /*holder.itemView.setOnLongClickListener(object :View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                AlertDialog.Builder(context).setTitle("Delete Chat ?").
                setPositiveButton("Yes",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Firebase.database.reference.child("UserChat").child(user.userId.toString()).setValue(null)
                    }
                }).setNegativeButton("No",object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0?.dismiss()
                    }
                }).show()
                return false
            }
        })*/

        //this will update the last message in chat fragment.
        Firebase.database.reference.child("chats")
            .child(FirebaseAuth.getInstance().uid + user.userId)
           .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (snap in snapshot.children) {
                        holder.lastmsg.setText(snap.child("message").getValue(String::class.java))
                        holder.timestamp.setText(snap.child("timestamp").getValue(String::class.java))
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        holder.itemView.setOnClickListener {
            val i = Intent(context, ChatDetailActivity::class.java)
            i.putExtra("name", user.UserName)
            i.putExtra("profile", user.profilePic)
            i.putExtra("userId", user.userId)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}