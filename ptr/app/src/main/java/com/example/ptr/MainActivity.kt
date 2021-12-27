package com.example.ptr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_view.*
import kotlin.String

class MainActivity : AppCompatActivity() {
    var i=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //for in built adapter
        /*val items= arrayOf<Int>(1)
        val adapter= ArrayAdapter<Int>(this,android.R.layout.simple_list_item_1,items)
        list.adapter=adapter*/

      //for using custom view
      var items = arrayOf<Int>(1)
        val adapter=CustomAdapter(this,20,items,refresh)

        list.adapter=adapter

    }
}