package com.example.customtoolbar

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var btn:Button
    private lateinit var tv:TextView
    private lateinit var error:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //using toolbar as actionbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        btn=findViewById(R.id.button)
        tv=findViewById(R.id.textView)
        error=findViewById(R.id.textView2)
        setSupportActionBar(toolbar as Toolbar?)  //important step

        btn.setOnClickListener {
            Task().execute()
            //execute()
        }

    }

    /*private fun execute(){
        var records=""
        var er=""
        val url="jdbc:mysql://DESKTOP-UIB4SBO:3306/department"
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
            val connection:Connection=DriverManager.getConnection("jdbc:mysql://192.100.0.000:3306/department","root","maitry")

            val statement:Statement=connection.createStatement()

            val ans:ResultSet=statement.executeQuery("SHOW TABLES")

            while(ans.next()){
                records+=ans.getString(1)+"\n"
            }

        }catch (e:Exception){
            er=e.toString()
        }
        tv.text=records
                Toast.makeText(this@MainActivity, "ssssss", Toast.LENGTH_SHORT).show()
        if(er!="")
            error.text=er
    }*/

   inner class Task():AsyncTask<Void,Void,Void>(){
       var records=""
       var er=""

        override fun doInBackground(vararg p0: Void?): Void? {
            val connectionProps = Properties()
            connectionProps.put("user", "root")
            connectionProps.put("password", "maitry")

            val url="jdbc:mysql://DESKTOP-UIB4SBO:3306/department"
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance()
                val connection:Connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/department","root","maitry")

                val statement:Statement=connection.createStatement()

                val ans:ResultSet=statement.executeQuery("SHOW TABLES")

                while(ans.next()){
                    records+=ans.getString(1)+"\n"
                }
            }catch (e:Exception){
                er=e.toString()
            }
           // Toast.makeText(this@MainActivity, "connection successful", Toast.LENGTH_SHORT).show()
            return null
        }

        override fun onPostExecute(result: Void?) {
            tv.text=records
            Toast.makeText(this@MainActivity, "ssssss", Toast.LENGTH_SHORT).show()
           if(er!="")
               error.text=er

           super.onPostExecute(result)
        }

    }
     //this creates menu on toolbar(3 dots)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.i1-> Toast.makeText(this, "broadcasting", Toast.LENGTH_SHORT).show()
            R.id.i2-> Toast.makeText(this, "set items", Toast.LENGTH_SHORT).show()
            R.id.i3-> Toast.makeText(this, "create new group", Toast.LENGTH_SHORT).show()
            else->{
                Toast.makeText(this, "n", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}