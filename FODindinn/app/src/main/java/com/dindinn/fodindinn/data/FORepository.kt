package com.dindinn.fodindinn.data

import com.dindinn.fodindinn.R
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FORepository {

    fun getFoodItems() : Observable<List<FoodItem>> = Observable.fromCallable<List<FoodItem>> {
        Thread.sleep(2000)
        listOf(
            FoodItem(
                44365525,
                R.drawable.image_8,
                20,
                "Pizza",
                "Cheese Pizaa with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_8,
                20,
                "Pizza",
                "Cheese Pizaa with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_8,
                20,
                "Pizza",
                "Cheese Pizaa with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_8,
                20,
                "Pizza",
                "Cheese Pizaa with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_8,
                20,
                "Pizza",
                "Cheese Pizaa with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_8,
                20,
                "Pizza",
                "Cheese Pizaa with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            ),
            FoodItem(
                44365525,
                R.drawable.image_8,
                20,
                "Pizza",
                "Cheese Pizaa with Rich taste." ,
                "https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/44373538/1/?bust=1554262696&width=1080"

            )
        )
    }.subscribeOn(Schedulers.io())

    fun getFoodItem(foodItem:  FoodItem) = Single.just(foodItem)
        .delaySubscription(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
}
