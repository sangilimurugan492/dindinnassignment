package com.dindinn.fodindinn

import com.dindinn.fodindinn.data.ImageModel

class Constants {



    companion object {
        private val imageArray = intArrayOf(
            R.drawable.image_12,
            R.drawable.image_13,
            R.drawable.image_14,
            R.drawable.image_15,
            R.drawable.image_8
        )
        fun getImageModel() : List<ImageModel> {
            val items = mutableListOf<ImageModel>()
            for (i in imageArray.indices) {
                val obj = ImageModel()
                obj.image = imageArray.get(i)
                obj.name = "Pizaa"
                obj.brief = "Chees Pizaa"
                obj.qty = "$200"
                items.add(obj)
            }
            return items
        }
    }
}