package com.alpha07.foodapp

import adapters.OrderAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import databases.DBHelper
import models.OrderModel

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        supportActionBar?.title = "My Orders"

        val dbHelper = DBHelper(this)
        val list = dbHelper.getOrder()
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView2)
        val ad = OrderAdapter(list,this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ad
    }
}