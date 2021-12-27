package com.example.whatsappclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.adapters.MessageAdapter
import com.example.whatsappclone.models.Messages
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chat_detail.*
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.fragment_chat_user_details.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatDetailActivity : AppCompatActivity() {

    val database=Firebase.database
    private val auth=FirebaseAuth.getInstance()
    private val messages=ArrayList<Messages>()
   lateinit var msgRecycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_detail)

        msgRecycler=findViewById(R.id.chats)

        val adapter=MessageAdapter(this,messages)
        msgRecycler.adapter=adapter
        msgRecycler.layoutManager=LinearLayoutManager(this)
        val sendmsg=findViewById<EditText>(R.id.sendMsg)

        val senderId=auth.uid   //that is the person which is logged in
        val receiverId=intent.getStringExtra("userId")
        val name=intent.getStringExtra("name")
        val dp=intent.getStringExtra("profile")

        nameofuser.text=name
        if(dp?.isEmpty() == false)
        Picasso.get().load(dp).into(profiledp)

        back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        send.setOnClickListener {
            if(sendmsg.text.toString().isEmpty()){
                sendmsg.setError("Please enter message")
                return@setOnClickListener
            }
            val msg=sendmsg.text.toString()
            val message= Messages()
            message.timestamp= SimpleDateFormat("hh:mm").format(Date()).toString()
            message.message=msg
            message.uId=senderId
            //made new for delete any msg
            message.receiverId=receiverId
            sendmsg.setText("")

            //writing / saving data in firebase
            database.reference.child("chats").child(senderId+receiverId).push().setValue(message).addOnSuccessListener {
                database.reference.child("chats").child(receiverId+senderId).push().setValue(message).addOnSuccessListener {

                }
            }
        }

        toolbar2.setOnClickListener {
            val sheet=BottomSheetDialog(this)
            sheet.setContentView(R.layout.fragment_chat_user_details)

            sheet.show()
        }

        //reading data from database.
        database.reference.child("chats").child(senderId+receiverId).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                messages.clear()
                for (snap in snapshot.children){
                    val model=snap.getValue(Messages::class.java)
                    if (model != null) {
                        //to delete msg we are setting id
                        model.messageId=snap.key
                        messages.add(model)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}