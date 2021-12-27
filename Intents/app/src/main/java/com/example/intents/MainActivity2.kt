package com.example.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

//this activity is made for explicit intents to go from one activity to another activity.

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //for explicit intent
        button2.setOnClickListener {
            val k= Intent(this, MainActivity::class.java).apply {
                Toast.makeText(this@MainActivity2, button2.text.toString(), Toast.LENGTH_SHORT).show()
            }
            startActivity(k)
        }

    }
}