package com.example.ptr

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_view.*
import kotlinx.android.synthetic.main.card_view.view.*
import java.util.*
import java.util.logging.Handler

class CustomAdapter(context: Context,var resource:Int,var list:Array<Int>,val srl:SwipeRefreshLayout):ArrayAdapter<Int> (context,resource,list){
 var i=1
    lateinit var parent:ViewGroup
    override fun getItem(position: Int): Int? {
        return list[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view=LayoutInflater.from(context).inflate(R.layout.card_view,parent,false)

        val x=getItem(position)
        var t=view.t1
        t.text=x.toString()

        srl.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener(){
            t.text=i++.toString()
            srl.isRefreshing=false
        })

        return view
    }

    /*private fun refresh() {

    }*/


}