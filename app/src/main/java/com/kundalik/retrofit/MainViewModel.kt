package com.kundalik.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kundalik.retrofit.model.Post
import com.kundalik.retrofit.utils.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2: MutableLiveData<Response<Post>> = MutableLiveData()
    val myCustomPosts: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myCustomPosts2: MutableLiveData<Response<List<Post>>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }

    fun getPost2(number: Int) {
        viewModelScope.launch {
            val response2 = repository.getPost2(number)
            myResponse2.value = response2
        }
    }

    fun getCustomPosts(userId: Int) {
        viewModelScope.launch {
            val response3 = repository.getCustomPost(userId)
            myCustomPosts.value = response3

        }
    }

    fun getCustomPosts2(userId: Int, sort: String, order: String) {
        viewModelScope.launch {
            val response4 = repository.getCustomPost2(userId, sort, order)
            myCustomPosts2.value = response4
        }
    }
}