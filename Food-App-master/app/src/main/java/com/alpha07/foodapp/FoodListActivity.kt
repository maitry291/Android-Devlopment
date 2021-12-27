package com.alpha07.foodapp

import adapters.FoodListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import models.FoodListModel

class FoodListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        val list = ArrayList<FoodListModel>()

        list.add(FoodListModel(R.drawable.pizza,
            "Pizza",
            "499 INR",
            "topped with some combination of olive oil, oregano, tomato, olives, mozzarella or other cheese, and many other ingredients"))
        list.add(FoodListModel(R.drawable.burger,
            "Burger",
            "99 INR",
            "consisting of one or more cooked patties—usually ground meat, typically beef—placed inside a sliced bread roll or bun"))
        list.add(FoodListModel(R.drawable.donuts,
            "Donuts",
            "199 INR",
            "glazed with a sugar icing, spread with icing or chocolate, or topped with powdered sugar, cinnamon, sprinkles or fruit"))
        list.add(FoodListModel(R.drawable.fries,
            "Fries",
            "49 INR",
            "pieces of potato that have been deep-fried"))
        list.add(FoodListModel(R.drawable.idli_dosa,
            "Idli Dosa",
            "149 INR",
            "avoury rice cake, originating from the Indian subcontinent, popular as breakfast foods in Southern India"))
        list.add(FoodListModel(R.drawable.momos,
            "Momos",
            "39 INR",
            "steamed dumpling with some form of filling."))
        list.add(FoodListModel(R.drawable.samosa,
            "Samosa",
            "29 INR",
            "baked pastry with a savory filling like spiced potatoes, onions, peas, chicken and other meats, or lentils"))
        list.add(FoodListModel(R.drawable.panipuri,
            "Panipuri",
            "29 INR",
            "hollow puri, filled with a mixture of flavored water, tamarind chutney, chili powder, chaat masala, potato mash, onion or chickpeas"))
        list.add(FoodListModel(R.drawable.pasta,
            "Pasta",
            "59 INR",
            "made from an unleavened dough of wheat flour mixed with water or eggs, and formed into sheets or other shapes"))
        list.add(FoodListModel(R.drawable.paubhaji,
            "Pav Bhaji",
            "79 INR",
            "consisting of a spicy vegetable gravy served with soft dinner rolls"))
        list.add(FoodListModel(R.drawable.shake,
            "Shake",
            "249 INR",
            "a sweet drink made by blending milk, ice cream, and flavorings or sweeteners"))

        val ad = FoodListAdapter(list,this)
        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ad

        findViewById<FloatingActionButton>(R.id.floatingActionButton2).setOnClickListener {
            val intent = Intent(this,OrderActivity::class.java)
            this.startActivity(intent)
            intent.putExtra("type","order")
        }


    }
}