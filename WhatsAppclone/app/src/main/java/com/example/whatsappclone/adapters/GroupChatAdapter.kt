package com.example.whatsappclone.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.R
import com.example.whatsappclone.models.Messages
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class GroupChatAdapter(val context: Context, private val list: ArrayList<Messages>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val SENDER_VIEW=1
        private const val RECEIVER_VIEW=2
    }

    override fun getItemViewType(position: Int): Int {
        if(list[position].uId== FirebaseAuth.getInstance().uid){  //i.e. sender which is currently logged in in the app
            return SENDER_VIEW
        }
        else
            return RECEIVER_VIEW
    }

    open class SenderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
    open class ReceiverViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType== SENDER_VIEW){
            val view= LayoutInflater.from(context).inflate(R.layout.sender_layout,parent,false)
            return SenderViewHolder(view)
        }
        else{
            val view= LayoutInflater.from(context).inflate(R.layout.receiver_layout,parent,false)
            return ReceiverViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val msg=list[position]

        //to delete any message in the chat (only in the sender side ) i.e. receiver will have tht msg
        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(context).setTitle("Delete message from me ?")
                .setPositiveButton("Yes") { p0, p1 ->
                    val ref = Firebase.database.reference.child("GroupChat")
                        .child(msg.messageId.toString()).setValue(null)
                }.setNegativeButton("No"
                ) { p0, p1 -> p0?.dismiss() }.show()

            false
        }

        if(getItemViewType(position)== SENDER_VIEW){
            holder.itemView.findViewById<TextView>(R.id.sender_msg).text=msg.message
            holder.itemView.findViewById<TextView>(R.id.sender_time).text=msg.timestamp
        }
        else{
            holder.itemView.findViewById<TextView>(R.id.receiver_msg).text=msg.message
            holder.itemView.findViewById<TextView>(R.id.receiver_time).text=msg.timestamp
            //to show the name of receiver
            val recName=holder.itemView.findViewById<TextView>(R.id.receiver_name)
            recName.isVisible=true
            recName.text=msg.name
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}