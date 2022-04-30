package com.example.project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detailed_scheme.*

class DetailedScheme : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_scheme)

        schname.text=intent.getStringExtra("sname")
        schdept.text=intent.getStringExtra("sdept")
        schtype.append(intent.getStringExtra("stype"))
        schcaste.text=intent.getStringExtra("caste")
        schgender.text=intent.getStringExtra("gender")
        schage.append(intent.getIntExtra("slage",0).toString()+"-"+intent.getIntExtra("suage",0)+" years")
        schincome.append("Less than or equal to "+intent.getIntExtra("income",0))
        schdisability.text=intent.getStringExtra("disability")
        schinfo.text=intent.getStringExtra("sinfo")

        register.setOnClickListener {
            startActivity(Intent(this,Register::class.java))
        }

        feedback.setOnClickListener {
            //open pop up fragment to write review and store that review in database
        }
    }
}