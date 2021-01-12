package com.dindinn.fodindinn.data.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    companion object {
        var newInstance : Retrofit? = null

        fun getInstance() : Retrofit {
            if (newInstance == null) {
                newInstance = Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
            }
            return newInstance as Retrofit
        }
    }
}