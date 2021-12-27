package com.example.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val list:ArrayAdapter<*>
        val list1:Contact_Adapter
       // val items= arrayOf<String>("Pizza","Burger","Kabab-roll","baby","bae","9687482595")
        /*var contactList= arrayOf(Contact("Maitry","9687482595"),Contact("Person 1","70967895")
        ,Contact("Person 2","70967895"))*/

        var contactList=Array<Contact>(10){
            Contact("Person ","70967895")
        }

       // list= ArrayAdapter(this,android.R.layout.simple_list_item_1,items)
       // l1.adapter=list
       // list1= My_Adapter(this,20,items)

       /* l1.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "item $i", Toast.LENGTH_SHORT).show()
        })*/
        //l1.adapter=list1
        list1=Contact_Adapter(this,20,contactList)
        l1.adapter=list1


    }
}