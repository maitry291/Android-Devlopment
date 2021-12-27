package com.example.myapplication2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var r1:Boolean = false;var r2:Boolean = false
    var r3:Boolean = false;var r4:Boolean = false

    var res:Double=0.0
    var ans:Double=0.0

   // @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        b1.setOnClickListener(View.OnClickListener{
            et1.append(b1.text)
           // et2.append(b1.text)
        })
        b2.setOnClickListener(View.OnClickListener{
            et1.append(b2.text)
           // et2.append(b2.text)
        })
        b3.setOnClickListener(View.OnClickListener{
            et1.append(b3.text)
        })
        b4.setOnClickListener(View.OnClickListener{
            et1.append(b4.text)
        })
        b5.setOnClickListener(View.OnClickListener{
            et1.append(b5.text)
        })
        b6.setOnClickListener(View.OnClickListener{
            et1.append(b6.text)
        })
        b7.setOnClickListener(View.OnClickListener{
            et1.append(b7.text)
        })
        b8.setOnClickListener(View.OnClickListener{
            et1.append(b8.text)
        })
        b9.setOnClickListener(View.OnClickListener{
            et1.append(b9.text)
        })
        b0.setOnClickListener(View.OnClickListener{
            et1.append(b0.text)
        })
       bp.setOnClickListener(View.OnClickListener {
           et1.append(bp.text)
       })
       bplus.setOnClickListener(View.OnClickListener{
           res=et1.text.toString().toDouble()
           r1=true;
           et2.text=et1.text
           et2.append(bplus.text)
           et1.text=null
       })
       bminus.setOnClickListener(View.OnClickListener{
           res=et1.text.toString().toDouble()
           r2=true;
           et2.text=et1.text
           et2.append(bminus.text)
           et1.text=null
       })
       bmulti.setOnClickListener(View.OnClickListener{
           res=et1.text.toString().toDouble()
           r3=true;
           et2.text=et1.text
           et2.append(bmulti.text)
           et1.text=null
       })
       bdiv.setOnClickListener(View.OnClickListener{
          res=et1.text.toString().toDouble()
           r4=true;
           et2.text=et1.text
           et2.append(bdiv.text)
           et1.text=null
       })
       bc.setOnClickListener(View.OnClickListener{
           et1.text=null
           et2.text=null
       })
       beq.setOnClickListener(View.OnClickListener{

          if(r1){
              et2.append(et1.text)
              ans=res+et1.text.toString().toDouble()
              et1.text=null
              r1=false
              et2.append(beq.text)
              et2.append(ans.toString())
          }
           if(r2){
               et2.append(et1.text)
               ans=res-et1.text.toString().toDouble()
               et1.text=null
               r2=false
               et2.append(beq.text)
               et2.append(ans.toString())
           }
           if(r3){
               et2.append(et1.text)
               ans=res*et1.text.toString().toDouble()
               et1.text=null
               r3=false
               et2.append(beq.text)
               et2.append(ans.toString())
           }
           if(r4){
               et2.append(et1.text)
               ans=res/et1.text.toString().toDouble()
               et1.text=null
               r4=false
               et2.append(beq.text)
               et2.append(ans.toString())
           }
       })



    }
}