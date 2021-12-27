package com.example.customtoolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //using toolbar as actionbar
        setSupportActionBar(toolbar as Toolbar?)  //important step

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