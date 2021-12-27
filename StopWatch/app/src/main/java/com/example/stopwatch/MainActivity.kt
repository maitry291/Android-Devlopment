package com.example.stopwatch

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.timer
import kotlin.properties.Delegates
import java.lang.Void as Void1

class MainActivity : AppCompatActivity() {

   var t by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            var b=minutes.text.toString().toInt()
            var a=seconds.text.toString().toInt()
            var c=hours.text.toString().toInt()

            while (b>=0){
                if(a==0)
                    b--
                var s:String= "$c:$b:"
                timer.text=s
                StopWatch(timer,s).execute(seconds.text.toString().toInt())
                b--
                s=hours.text.toString()+":"+b.toString()+":"
                timer.isVisible=true
                if(b==0&&c!=0){
                    b=59
                    c--
                }
                if(b==0&&c==0)
                    break
            }
        }
//        if(a==0&&b==0&&c==0)
//            timeup.isVisible=true
    }

    class StopWatch(var timer:TextView,var s:String) : AsyncTask<Int, Int, Int>() { //this class is made to use properties of class AsyncTask
        //AsyncTask must be subclassed to be used. The subclass will override at least one method (doInBackground),
        // and most often will override a second one (onPostExecute.)


        //this method is not called directly by main activity/main thread ,we call it by writing execute.

        override fun doInBackground(vararg p0: Int?): Int {
            var n=p0[0]
            if(n==0)
                n=59
            var x=n
            if (x != null) {
                while (x>=0){
                    val currentTime:Long=System.currentTimeMillis()
                    while(System.currentTimeMillis()<currentTime+1000){}
                    publishProgress(x)
                    x--
                }
            }
            return 0
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            timer.text=s+values[0].toString()
            timer.isVisible=true
        }

    }

}