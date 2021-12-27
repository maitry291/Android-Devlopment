package com.example.whatsappclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.adapters.GroupChatAdapter
import com.example.whatsappclone.adapters.MessageAdapter
import com.example.whatsappclone.models.Messages
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_group_chat.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class GroupChatActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()
    private val groupChat = ArrayList<Messages>()
    private val chatRef = Firebase.database.getReference("GroupChat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_chat)

        val senderId = auth.uid
        //setting group chat name
        groupName.setText("Friends Group")

        val chatAdapter = GroupChatAdapter(this, groupChat)
        val chatRecycler = findViewById<RecyclerView>(R.id.groupChats)

        chatRecycler.adapter = chatAdapter
        chatRecycler.layoutManager = LinearLayoutManager(this)

        backArrow.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        //to save chat messages in the database first we need to send msg
        sendInGroup.setOnClickListener {
            val message=sendMsgInGroup.text.toString()
            val msgModel=Messages()
            msgModel.uId=senderId
            msgModel.message=message
            msgModel.timestamp= SimpleDateFormat("hh:mm").format(Date()).toString()

            //this will add name in the msg
            FirebaseDatabase.getInstance().getReference("UserChat").child(senderId.toString()).
            addValueEventListener(object :ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(com.example.whatsappclone.models.UserInfo::class.java)
                    msgModel.name= user!!.UserName
                    //after assigning username we have to upload msgmodel in database in this block
                    //if we do below 2 steps outside of this block then msdmodel.name will be empty.

                    //we have to make edit text empty
                    sendMsgInGroup.setText("")

                    //this will create a node and will save the msg
                    chatRef.push().setValue(msgModel).addOnSuccessListener { }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }

        //to load group msgs in recycler view
        chatRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                groupChat.clear()
                for (snap in snapshot.children){
                    val model=snap.getValue(Messages::class.java)
                    if (model != null) {
                        //to delete msg
                        model.messageId=snap.key
                        groupChat.add(model)
                    }
                }
                chatAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}