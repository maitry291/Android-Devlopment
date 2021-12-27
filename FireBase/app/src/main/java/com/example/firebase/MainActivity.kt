package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db=Firebase.database  //gives the database reference/root node ref
        val userRef=db.getReference("User")  //makes child named User in the root node

        val users=ArrayList<UserInfo>()
        val adapter=ChatAdapter(this,users)

        chatRecycler.adapter=adapter
        chatRecycler.layoutManager=LinearLayoutManager(this)

        userRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user: UserInfo? =snapshot.getValue(UserInfo::class.java)
                if (user != null) {
                    users.add(user)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

       /* //to add node in recycler from database
        userRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val user: UserInfo? =snapshot.getValue(UserInfo::class.java)
                if (user != null) {
                    users.add(user)
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

//        //UserInfo is a custom class which takes 3 values in it.
//        val user1=UserInfo()
//        user1.UserName="Maitry"
//        user1.lastmsg="okay,bye..."
//        user1.timestamp="10:00 am"
//        val user2=UserInfo()
//        user2.UserName="Jeel"
//        user2.lastmsg="Heyyy..."
//        user2.timestamp="11:32 am"
//        val user3=UserInfo()
//        user3.UserName="Roshan"
//        user3.lastmsg="Sunn.."
//        user3.timestamp="11:50 am"
//
//        userRef.push().setValue(user1)  //we are setting user variable in the child node "User"
//        userRef.push().setValue(user2)
//        userRef.push().setValue(user3)

    }
}