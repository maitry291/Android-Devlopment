package com.alpha07.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import databases.DBHelper

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val dbHelper = DBHelper(this)
        val quantity = findViewById<TextView>(R.id.item_quantity)

        // New Order (Intent from food list activity)
        if (intent.getIntExtra("type", 0) == 1) {
            val image = intent.getIntExtra("image", 0)
            val name = intent.getStringExtra("name")
            val price = intent.getStringExtra("price")
            val description = intent.getStringExtra("description")


            findViewById<ImageView>(R.id.item_image).setImageResource(image)
            findViewById<TextView>(R.id.item_name).text = name
            findViewById<TextView>(R.id.item_price).text = price
            findViewById<TextView>(R.id.item_description).text = description

            findViewById<AppCompatButton>(R.id.btnAddToCart).setOnClickListener {
                val isInserted: Boolean = dbHelper.insertOrder(
                    image, name, price,
                    quantity.text as String?, description
                )

                if (isInserted)
                    Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        if (intent.getIntExtra("type", 0) == 2) {
            val id = intent.getStringExtra("id")?.toInt()
            val cursor = dbHelper.getOrderByID(1)
           // Toast.makeText(this, id.toString(), Toast.LENGTH_SHORT).show()

            while (cursor.moveToNext()) { //if-->while
                if (cursor.getString(0) == id.toString()) {
                    findViewById<ImageView>(R.id.item_image).setImageResource(
                        cursor.getString(1).toInt()
                    )
                    findViewById<TextView>(R.id.item_name).text = cursor.getString(2)
                    findViewById<TextView>(R.id.item_price).text = cursor.getString(3)
                    quantity.text = cursor.getString(4)
                    findViewById<TextView>(R.id.item_description).text = cursor.getString(5)
                }
            }
            findViewById<AppCompatButton>(R.id.btnAddToCart).text = "Update Order"

        }

        findViewById<FloatingActionButton>(R.id.floatingActionButton2).setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            this.startActivity(intent)
        }

    }
}