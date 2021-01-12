package com.dindinn.fodindinn.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.dindinn.fodindinn.R
import com.dindinn.fodindinn.data.FoodItem
import com.google.android.material.imageview.ShapeableImageView

class ItemAdapter() : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var listModels: List<Any>? = null

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.item_text)
        val tvDescription: TextView = itemView.findViewById(R.id.item_description)
        val tvPrice: TextView = itemView.findViewById(R.id.item_price)
        val ivItem: ShapeableImageView = itemView.findViewById(R.id.item_image)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_item_view,
                parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(listModels!= null) {
            listModels!!.size
        } else {
            0
        }
    }

    fun setMenu(menuModels: List<Any>) {
        this.listModels = menuModels
        notifyDataSetChanged()
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listModels != null) {
            if (listModels!![position] is FoodItem) {
                val menuModel = listModels!![position] as FoodItem
                holder.tvName.text = menuModel.name
                holder.tvPrice.text = "$ " + menuModel.price.toString()
                holder.tvDescription.text = menuModel.description
                holder.ivItem.setImageResource(menuModel.image)

                holder.itemView.setOnClickListener {
                }
            }
        }

    }

}