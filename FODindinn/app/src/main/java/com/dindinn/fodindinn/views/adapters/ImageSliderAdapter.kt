package com.dindinn.fodindinn.views.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.balysv.materialripple.MaterialRippleLayout
import com.dindinn.fodindinn.data.ImageModel
import com.dindinn.fodindinn.R
import com.dindinn.fodindinn.Utils

class ImageSliderAdapter constructor(
    private val act: Activity,
    items: List<ImageModel>
) : PagerAdapter() {
    private var items: List<ImageModel>
    private var onItemClickListener: OnItemClickListener? = null

    public interface OnItemClickListener {
        fun onItemClick(view: View?, obj: ImageModel?)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getCount(): Int {
        return items.size
    }

    fun getItem(pos: Int): ImageModel {
        return items[pos]
    }

    fun setItems(items: List<ImageModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val o: ImageModel = items[position]
        val inflater = act.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflater.inflate(R.layout.item_slider_image, container, false)
        val image = v.findViewById<View>(R.id.image) as ImageView
        val lyt_parent: MaterialRippleLayout =
            v.findViewById<View>(R.id.lyt_parent) as MaterialRippleLayout
        Utils.displayImageOriginal(
            act,
            image,
            o.image
        )
        lyt_parent.setOnClickListener(View.OnClickListener { v ->
            if (onItemClickListener != null) {
                onItemClickListener!!.onItemClick(v, o)
            }
        })
        (container as ViewPager).addView(v)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as RelativeLayout)
    }

    // constructor
    init {
        this.items = items
    }
}