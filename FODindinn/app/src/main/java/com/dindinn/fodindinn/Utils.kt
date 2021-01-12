package com.dindinn.fodindinn

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class Utils {

    companion object {
        fun displayImageOriginal(
            ctx: Context?,
            img: ImageView?,
            @DrawableRes drawable: Int
        ) {
            try {
                Glide.with(ctx).load(drawable)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(img)
            } catch (e: Exception) {
            }
        }
    }
}