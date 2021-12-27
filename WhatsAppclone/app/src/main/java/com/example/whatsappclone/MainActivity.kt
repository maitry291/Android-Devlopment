package com.example.whatsappclone

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.whatsappclone.adapters.FragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
/*import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase*/
import kotlinx.android.synthetic.main.activity_login_screen.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.chat_layout.*

class MainActivity : AppCompatActivity() {

   lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar as Toolbar?)

        auth= Firebase.auth

        val tabLayout:TabLayout=findViewById(R.id.tablayout)

        // this code is for tabs (chat call and status)
        val adapter: FragmentAdapter = FragmentAdapter(supportFragmentManager)
        viewpager.adapter=adapter
        //if u use viewpager2 in activity main then u will have issue with adapter and setupviewpager both.
        //viewpager2 is the latest launched so to use that we have to extend fragment adapter with fragmentStateAdapter.
        tabLayout.setupWithViewPager(viewpager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.i2){
            Toast.makeText(this, "Logging out", Toast.LENGTH_SHORT).show()

            auth.signOut()
            val logoutIntent=Intent(this,LoginScreen::class.java)
            logoutIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(logoutIntent)
        }
        if(item.itemId==R.id.i1){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,Settings::class.java))
        }
        if(item.itemId==R.id.i3){
            Toast.makeText(this, "Group chat room", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,GroupChatActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}