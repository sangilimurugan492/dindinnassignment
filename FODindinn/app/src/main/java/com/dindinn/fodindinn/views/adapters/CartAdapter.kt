package com.dindinn.fodindinn.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.dindinn.fodindinn.data.ImageModel
import com.dindinn.fodindinn.R
import com.dindinn.fodindinn.data.PostModel
import com.google.android.material.imageview.ShapeableImageView

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private lateinit var listModels: List<Any>
//    private lateinit var clickListener: ItemClickListener

//    fun addListener(itemClickListener: ItemClickListener) {
//        clickListener = itemClickListener
//    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItem = itemView.findViewById<ShapeableImageView>(R.id.iv_item)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        val ivClose = itemView.findViewById<ImageView>(R.id.iv_close)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclar_cart_view,
                parent,
                false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listModels.size
    }

    fun setMenu(menuModels: List<Any>) {
        this.listModels = menuModels
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (listModels[position] is ImageModel) {
            val menuModel = listModels[position] as ImageModel
            holder.tvName.text = menuModel.name
            holder.tvPrice.text = menuModel.qty
            holder.ivItem.setImageResource(menuModel.image)

            holder.itemView.setOnClickListener {
//                clickListener.onItemSelected(listModels[position])
            }
        } else if (listModels[position] is PostModel) {
                val menuModel = listModels[position] as PostModel
                holder.tvName.text = menuModel.title
                holder.tvPrice.text = menuModel.body
//                holder.ivItem.setImageResource(menuModel.image)

                holder.itemView.setOnClickListener {
//                clickListener.onItemSelected(listModels[position])
                }
            }

    }

}