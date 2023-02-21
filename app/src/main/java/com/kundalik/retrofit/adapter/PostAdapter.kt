package com.kundalik.retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kundalik.retrofit.R
import com.kundalik.retrofit.model.Post
import kotlinx.android.synthetic.main.row_layout_post.view.*

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    private var myList = emptyList<Post>()


    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout_post, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        holder.itemView.tv_user_id.text = myList[position].userId.toString()
        holder.itemView.tv_id_txt.text = myList[position].id.toString()
        holder.itemView.tv_title.text = myList[position].title
        holder.itemView.tv_body_txt.text = myList[position].body

    }

    fun setData(newList: List<Post>) {
        myList = newList
        notifyDataSetChanged()

    }
}