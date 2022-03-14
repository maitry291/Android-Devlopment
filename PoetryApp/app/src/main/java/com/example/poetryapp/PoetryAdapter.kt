package com.example.poetryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PoetryAdapter(var context:Context,var list:ArrayList<PoetryModel>):RecyclerView.Adapter<PoetryAdapter.ViewHolder>() {

    open class ViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView){
        var poetName=itemView.findViewById<TextView>(R.id.poet_name)
        var poetry=itemView.findViewById<TextView>(R.id.poetry)
        var date=itemView.findViewById<TextView>(R.id.poetry_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(context).inflate(R.layout.poetry_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model=list[position]
        holder.poetName.text=model.poetName
        holder.poetry.text=model.poetry
        holder.date.text=model.dateTime
    }

    override fun getItemCount(): Int {
        return list.size
    }
}