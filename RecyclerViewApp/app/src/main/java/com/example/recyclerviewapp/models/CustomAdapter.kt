package com.example.recyclerviewapp.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.R

/*onCreateViewHolder(): This function sets the views to display the items.
onBindViewHolder(): This function is used to bind the list items to our widgets such as TextView,
ImageView, etc.
getItemCount(): It returns the count of items present in the list.
*/

class CustomAdapter(private val mList:List<ItemsViewModel>):RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    //create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //ViewGroup is generally used to define the layout in which views(widgets)
        // will be set/arranged/listed on the android screen.
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemsViewModel class
        holder.imageView.setImageResource(itemsViewModel.image)

        // sets the text to the textview from our itemsViewModel class
        holder.textView.text = itemsViewModel.text

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }
    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.i1)
        val textView: TextView = itemView.findViewById(R.id.t1)
    }

}
