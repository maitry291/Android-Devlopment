package com.example.whatsappclone.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.R
import com.example.whatsappclone.adapters.ChatAdapter
import com.example.whatsappclone.models.Messages
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_chat.*
import java.util.ArrayList

class ChatFragment() : Fragment() {

    private val auth=Firebase.auth
    private val db=Firebase.database
    private var chats=ArrayList<com.example.whatsappclone.models.UserInfo>()
   lateinit var chatRecycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        chatRecycler=view.findViewById(R.id.chatRecycler)

        val adapter=ChatAdapter(requireContext(),chats)
        chatRecycler.adapter=adapter
        chatRecycler.layoutManager=LinearLayoutManager(requireContext())

        db.reference.child("UserChat").orderByChild("timestamp").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                chats.clear()
                for (snap in snapshot.children){
                    val model=snap.getValue(com.example.whatsappclone.models.UserInfo::class.java)
                    model?.userId=snap.key
                        if (model != null&&model.userId!=auth.uid) {
                            chats.add(model)
                        }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        //this method loads userchat many times in the adapter.
       /* db.getReference("UserChat").addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chat=snapshot.getValue(com.example.whatsappclone.models.UserInfo::class.java)
                chat?.userId=snapshot.key //this is an imp step as from here we are getting id of the user which will be used in reciever id.
                if (chat != null) {
                    chats.add(chat)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
*/
        return view
    }


}



