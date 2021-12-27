package com.example.permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock.sleep
import java.lang.Exception

//In manifest.xml intent filter are put in the activity which is launched first while starting app

class IntroScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_screen)

        supportActionBar?.hide()

        val t= Thread(Runnable {     //syntax is like this do remember
            try {
                sleep(3000)
            }
            catch (e:Exception){
                println(e)
            }
            finally {
                val i=Intent(this@IntroScreen,MainActivity::class.java)
                startActivity(i)
            }
        })
        t.start()

    }
}