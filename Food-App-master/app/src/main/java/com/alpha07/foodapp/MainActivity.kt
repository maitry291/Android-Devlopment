package com.alpha07.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setSupportActionBar(toolbar as androidx.appcompat.widget.Toolbar)


        val loginBtn:Button = findViewById(R.id.loginBtn)
        val regBtn:Button = findViewById(R.id.regBtn)
        val skipBtn:TextView = findViewById(R.id.tvSkip)

        loginBtn.setOnClickListener {
            Toast.makeText(this, "Login Page", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }

        regBtn.setOnClickListener {
            Toast.makeText(this, "Register Page", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this,registerActivity::class.java)
            startActivity(intent)
        }

        skipBtn.setOnClickListener {
            val intent = Intent(this,FoodListActivity::class.java)
            startActivity(intent)
        }
    }

    // Inflating menu to Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            // Code to performed when menuItem clicked

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