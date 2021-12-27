package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var score=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        btn.setOnClickListener {
            val ans1=rg1.checkedRadioButtonId  //int
            if(ans1!=-1){
                val a1=findViewById<RadioButton>(ans1)
                if(a1.text.toString()=="Delhi")
                    score++
            }
            val ans2=rg2.checkedRadioButtonId  //int
            if(ans2!=-1){
                val a2=findViewById<RadioButton>(ans2)
                if(a2.text.toString()=="Sardar Vallabh Patel")
                    score++
            }
            Toast.makeText(this, "Your score is $score/2", Toast.LENGTH_SHORT).show()
        }
    }
}