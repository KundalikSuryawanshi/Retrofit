package com.kundalik.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kundalik.retrofit.adapter.PostAdapter
import com.kundalik.retrofit.model.Post
import com.kundalik.retrofit.utils.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val postAdapter by lazy { PostAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getCustomPosts2(2, "id", "asc")

        viewModel.myCustomPosts2.observe(this, Observer { response ->

            if (response.isSuccessful) {
                response.body()?.let { postAdapter.setData(it) }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        // post request
        val myPost = Post("programming is thinking not a typing", 2, "android application", 2)
        viewModel.pushPost(myPost)
        viewModel.myResponse.observe(this, Observer {response ->
            if (response.isSuccessful) {
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.message().toString())
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
                //201 response code indicate that request has succeed.
            }
        })

        viewModel.pushPost2(2, 2, "android developer", "keep working hard")

    }

    private fun setupRecyclerView() {
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

//        val options: HashMap<String, String> = HashMap()
//        options.put("sort","id")
//        options.put("_order", "desc")
//
//        viewModel.getPost()
//        viewModel.myResponse.observe(this, Observer { response ->
//            if (response.isSuccessful) {
//                Log.d("Response", response.body()?.id.toString())
//                Log.d("Response", response.body()?.userId.toString())
//                Log.d("Response", response.body()?.title!!)
//                Log.d("Response", response.body()?.body!!)
//                textView.text = response.body()?.title!!
//            } else {
//                Log.d("Response", response.errorBody().toString())
//                textView.text = response.code().toString()
//            }
//        })
//
//        button.setOnClickListener {
//
//            val myNumber = number_edit_text.text.toString()
//            if (myNumber.isNotEmpty()) {
//                viewModel.getCustomPosts3(Integer.parseInt(myNumber),options)
//
//                viewModel.myCustomPosts2.observe(this, Observer { response ->
//                    if (response.isSuccessful) {
//                        textView.text = response.body().toString()
//                        response.body()?.forEach {
//                            Log.d("Response", it.userId.toString())
//                            Log.d("Response", it.id.toString())
//                            Log.d("Response", it.title)
//                            Log.d("Response", it.body)
//                            Log.d("Response","----------------------")
//                        }
//                    } else {
//                        textView.text = response.code().toString()
//                    }
//                })


//            val myNumber = number_edit_text.text.toString()
//            if (myNumber.isNotEmpty()) {
//                viewModel.getCustomPosts2(Integer.parseInt(myNumber),"id", "asc")
//
//                viewModel.myCustomPosts2.observe(this, Observer { response ->
//                    if (response.isSuccessful) {
//                        textView.text = response.body().toString()
//                        response.body()?.forEach {
//                            Log.d("Response", it.userId.toString())
//                            Log.d("Response", it.id.toString())
//                            Log.d("Response", it.title)
//                            Log.d("Response", it.body)
//                            Log.d("Response","----------------------")
//                        }
//                    } else {
//                        textView.text = response.code().toString()
//                    }
//                })


//                viewModel.myCustomPosts.observe(this, Observer { response ->
//                    if (response.isSuccessful) {
//                        textView.text = response.body().toString()
//                        response.body()?.forEach {
//                            Log.d("Response", it.userId.toString())
//                            Log.d("Response", it.id.toString())
//                            Log.d("Response", it.title)
//                            Log.d("Response", it.body)
//                            Log.d("Response","----------------------")
//                        }
//                    } else {
//                        textView.text = response.code().toString()
//                    }
//                })
//                } else {
//                Toast.makeText(this, "enter number", Toast.LENGTH_SHORT).show()
//            }
//
//        }

//        button.setOnClickListener {
//            val myNumber = number_edit_text.text.toString()
//            viewModel.getPost2(Integer.parseInt(myNumber))
//            viewModel.myResponse2.observe(this, Observer {response ->
//                if (response.isSuccessful) {
//                    textView.text = response.body().toString()
//                } else {
//                    textView.text = response.code().toString()
//                }
//            })
//        }
//
//}
//}