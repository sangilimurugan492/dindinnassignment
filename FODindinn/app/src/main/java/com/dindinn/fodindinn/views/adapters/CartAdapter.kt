package com.dindinn.fodindinn.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.dindinn.fodindinn.ItemClickListener
import com.dindinn.fodindinn.R
import com.dindinn.fodindinn.data.FoodItem
import com.dindinn.fodindinn.data.PostModel
import com.google.android.material.imageview.ShapeableImageView

class CartAdapter(val isFrom : String) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private var listModels: MutableList<Any>? = null
    private var clickListener : ItemClickListener? = null

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItem = itemView.findViewById<ShapeableImageView>(R.id.iv_item)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        val ivClose = itemView.findViewById<ImageView>(R.id.iv_close)

    }

    fun addListener(itemClickListener: ItemClickListener) {
        clickListener = itemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view: View = if ("info" == isFrom) {
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_info_view, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycler_cart_view, parent, false)
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(listModels!= null) {
            listModels!!.size
        } else {
            0
        }
    }

    fun setMenu(menuModels: MutableList<Any>) {
        this.listModels = menuModels
        notifyDataSetChanged()
    }

    fun removeItem(position : Int) {
        listModels!!.remove(position)
        notifyDataSetChanged()
    }

    fun getItems() : MutableList<Any> {
        return listModels!!
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listModels != null) {
            if (listModels!![position] is FoodItem) {
                val menuModel = listModels!![position] as FoodItem
                holder.tvName.text = menuModel.name
                holder.tvPrice.text = "$ " + menuModel.price
                holder.ivItem.setImageResource(menuModel.image)
                holder.itemView.setOnClickListener {
                    clickListener!!.clicked(position)
                }
            } else if (listModels!![position] is PostModel) {
                val menuModel = listModels!![position] as PostModel
                holder.tvName.text = menuModel.title
                holder.tvPrice.text = menuModel.body
            }

        }
    }

}