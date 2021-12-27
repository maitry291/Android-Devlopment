package adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alpha07.foodapp.DetailsActivity
import com.alpha07.foodapp.R
import models.OrderModel

class OrderAdapter(val list: ArrayList<OrderModel>, val context: Context) : RecyclerView.Adapter<OrderAdapter.viewHolder>() {

    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.order_image1)
        val name: TextView = itemView.findViewById(R.id.order_name)
        val price: TextView? = itemView.findViewById(R.id.order_price)
        val id: TextView = itemView.findViewById(R.id.order_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val sampleViews =
            LayoutInflater.from(parent.context).inflate(R.layout.sample_order, parent, false)

        return viewHolder(sampleViews)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        val model: OrderModel = list[position]

        holder.image.setImageResource(model.image)
        holder.name.text = model.name
        holder.price?.text = model.price
        holder.id.text = model.id.toString()

        holder.itemView.setOnClickListener {
            val intent  = Intent(context,DetailsActivity::class.java)
            intent.putExtra("id",holder.id.text.toString())
            intent.putExtra("type",2)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}