package com.example.intents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.jvm.internal.Intrinsics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //for implicit intent
        //this block takes url as input and when we tap button url will open.
       /* button.setOnClickListener {
            val i=Intent(Intent.ACTION_VIEW)
            if (text.text.toString().isEmpty())
                Toast.makeText(this, "Please enter valid url", Toast.LENGTH_SHORT).show()
            else {
                i.setData(Uri.parse(text.text.toString()))
                startActivity(i)
            }
        }*/

        //for explicit intent
        button.setOnClickListener {
            val j=Intent(this, MainActivity2::class.java).apply {
                Toast.makeText(this@MainActivity, button.text.toString(), Toast.LENGTH_SHORT).show()
            }
            startActivity(j)
        }
    }
}