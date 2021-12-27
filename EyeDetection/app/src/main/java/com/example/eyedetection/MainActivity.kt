package com.example.eyedetection

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//in this project we used 3rd party library and made eye detecting video player in aap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        lookme.init(this)
        //offline video
        lookme.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.butterfly))

        //online video
        //lookme.setVideoPath("https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4")

        lookme.start()
        lookme.setLookMe()
    }
}