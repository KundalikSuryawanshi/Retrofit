package com.kundalik.retrofit.utils.repository

import com.kundalik.retrofit.api.RetrofitInstance
import com.kundalik.retrofit.model.Post
import retrofit2.Response


class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}