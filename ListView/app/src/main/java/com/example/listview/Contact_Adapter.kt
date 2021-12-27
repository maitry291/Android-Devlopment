package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.mailbox_layout.view.*

//for contact array type

class Contact_Adapter(context:Context,resource:Int,var list:Array<Contact>):ArrayAdapter<Contact>(context,resource,list) {
    override fun getItem(position: Int): Contact {
        return list[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView =
            LayoutInflater.from(context).inflate(R.layout.mailbox_layout, parent, false)

        var i = convertView.i1

        var x=getItem(position)

        convertView.t1.text="Name:"+x.name
        convertView.t2.text="Num:"+x.num


        convertView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "item " + (position + 1), Toast.LENGTH_SHORT).show()
        })

        return convertView
    }
}