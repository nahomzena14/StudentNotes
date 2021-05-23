package com.example.notescollector.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notescollector.R
import com.example.notescollector.model.Post
import kotlinx.android.synthetic.main.post_item_layout.view.*

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    //list of note
    var postList:List<Post> = mutableListOf()

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        //inflate item layout
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.post_item_layout,parent,false
        )
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        //assign text views
        val post = postList[position]
        holder.itemView.subject_textview.text = post.subject
        holder.itemView.note_textview.text = post.noteContent
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    //update list
    fun updateList(postList: List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }
}