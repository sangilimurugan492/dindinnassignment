package com.dindinn.fodindinn

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dindinn.fodindinn.data.FoodItem

data class FOState(
    val foodItems: Async<List<FoodItem>> = Uninitialized,
    val foodItemId: Long? = null,
    val singleItem: Async<FoodItem> = Uninitialized
) : MavericksState {
    val food: FoodItem? = foodItem(foodItemId)

    private fun foodItem(id: Long?): FoodItem? = foodItems()?.firstOrNull { it.id == id }
}
