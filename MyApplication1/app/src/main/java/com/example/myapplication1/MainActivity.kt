package com.example.myapplication1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      // val btn:Button=findViewById(R.id.btn) // not needed if activity_main is imported

        b1.setOnClickListener(View.OnClickListener {
          //higher order fun as it takes function as parameter.
         val res= e1.text.toString().toDouble()+e2.text.toString().toDouble()
            textView2.text=res.toString()
        })
        b2.setOnClickListener(View.OnClickListener {
            //higher order fun as it takes function as parameter.
            val res= e1.text.toString().toDouble()-e2.text.toString().toDouble()
            textView2.text=res.toString()
        })
        b3.setOnClickListener(View.OnClickListener {
            //higher order fun as it takes function as parameter.
            val res= e1.text.toString().toDouble()*e2.text.toString().toDouble()
            textView2.text=res.toString()
        })
        b4.setOnClickListener(View.OnClickListener {
            //higher order fun as it takes function as parameter.
            val res= e1.text.toString().toDouble()/e2.text.toString().toDouble()
            textView2.text=res.toString()
        })

    }
    /*fun showToast(view: android.view.View) {
        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()
    }*/

}