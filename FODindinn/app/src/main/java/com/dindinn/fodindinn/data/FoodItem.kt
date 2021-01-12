package com.dindinn.fodindinn.data

data class FoodItem (
    val id: Long,
    val image : Int,
    val price : Int,
    val name: String,
    val description: String,
    val imageUrl: String
) : StableItem {
    override val stableId = id
}