package com.dindinn.fodindinn.data.network

import com.dindinn.fodindinn.data.PostModel
import io.reactivex.Observable
import retrofit2.http.GET

interface APIInterface {
    @GET("posts")
    fun getPosts() : Observable<List<PostModel>>
}