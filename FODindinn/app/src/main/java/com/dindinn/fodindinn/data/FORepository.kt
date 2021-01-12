package com.dindinn.fodindinn.data

import com.dindinn.fodindinn.R
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FORepository {

    fun getFoodItems() : Observable<MutableList<FoodItem>> = Observable.fromCallable<MutableList<FoodItem>> {
        Thread.sleep(100)
        mutableListOf(
            FoodItem(
                44365525,
                R.drawable.image_8,
                46,
                "Pizza",
                "Cheese Pizza with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_12,
                84,
                "Burger",
                "Burger with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_15,
                30,
                "Veg-Sandwich",
                "Veg-Sandwich with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_14,
                80,
                "MoMo",
                "MoMo Aroma Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_13,
                90,
                "Black Current",
                "Black Current Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_8,
                80,
                "Cake",
                "IceCream Cake" ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_13,
                60,
                "Juice",
                "Creamy Juice" ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            )
        )
    }.subscribeOn(Schedulers.io())

    fun getFoodItem(foodItem:  FoodItem) = Single.just(foodItem)
        .delaySubscription(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
}
