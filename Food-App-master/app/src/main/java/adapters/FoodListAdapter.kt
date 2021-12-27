package adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alpha07.foodapp.DetailsActivity
import com.alpha07.foodapp.R
import models.FoodListModel

class FoodListAdapter(val list: ArrayList<FoodListModel>, val context: Context) :
    RecyclerView.Adapter<FoodListAdapter.viewHolder>() {

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.food_image)
        val name: TextView = itemView.findViewById(R.id.food_name)
        val price: TextView = itemView.findViewById(R.id.food_price)
        val description: TextView = itemView.findViewById(R.id.food_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val sampleViews =
            LayoutInflater.from(parent.context).inflate(R.layout.sample_food, parent, false)

        return viewHolder(sampleViews)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val model: FoodListModel = list[position]

        holder.image.setImageResource(model.image)
        holder.name.text = model.name
        holder.price.text = model.price
        holder.description.text = model.description

        holder.itemView.setOnClickListener{
            val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra("image",model.image)
            intent.putExtra("name",model.name)
            intent.putExtra("price",model.price)
            intent.putExtra("description",model.description)
            intent.putExtra("type",1)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
