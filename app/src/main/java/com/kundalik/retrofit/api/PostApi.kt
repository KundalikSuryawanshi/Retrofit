package com.kundalik.retrofit.api

import com.kundalik.retrofit.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {

    @GET("posts/1")
    suspend fun getPost(): Response<Post>
}