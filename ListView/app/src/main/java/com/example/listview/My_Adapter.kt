package com.example.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.mailbox_layout.view.*

//for string array type

@Suppress("ClassName")
class My_Adapter(context: Context, resources:Int, var list: Array<String>):
    ArrayAdapter<String>(context,resources,list){


    override fun getItem(position: Int): String {
       return list.get(position)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView=LayoutInflater.from(context).inflate(R.layout.mailbox_layout,parent,false)

        var t=convertView.t1
        var i=convertView.i1

        t.text=getItem(position)

        convertView.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "item "+(position+1), Toast.LENGTH_SHORT).show()
        })

        return convertView
    }

}