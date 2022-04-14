package com.example.project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import com.example.project.models.SchemesTable

class SchemesAdapter(val list:ArrayList<SchemesTable>,val context:Context) :
      RecyclerView.Adapter<SchemesAdapter.viewHolder>(){

    open class viewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var sname:TextView=itemView.findViewById(R.id.schemeName)
        var stype:TextView=itemView.findViewById(R.id.schemeType)
        var sinfo:TextView=itemView.findViewById(R.id.schemeDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.signle_scheme,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val scheme:SchemesTable=list.get(position)

        holder.sname.text=scheme.scheme_name
        holder.stype.text=scheme.scheme_type
        holder.sinfo.text=scheme.scheme_info


    }

    override fun getItemCount(): Int {
        return list.size
    }
}