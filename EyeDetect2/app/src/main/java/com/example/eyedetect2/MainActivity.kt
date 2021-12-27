package com.example.eyedetect2

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pd.lookatme.LookAtMe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val l:LookAtMe=findViewById(R.id.lookme)

        l.init(this)
        l.setVideoURI(Uri.parse("android.resource://"+packageName+"/"+R.raw.butterfly))

        l.start()
        l.setLookMe()
    }
}