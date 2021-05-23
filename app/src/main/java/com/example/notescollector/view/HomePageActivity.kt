package com.example.notescollector.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notescollector.R
import com.example.notescollector.adapter.PostAdapter
import com.example.notescollector.model.Post
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.posts_layout.*

class HomePageActivity:AppCompatActivity() {

    private val postAdapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.posts_layout)

        recyclerview.adapter = postAdapter

        FirebaseDatabase.getInstance().reference.child("NotePosts")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val postList = mutableListOf<Post>()
                    snapshot.children.forEach(){
                        it.getValue(Post::class.java)?.let {item->
                            postList.add(item)
                        }
                    }
                    postAdapter.updateList(postList)
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        add_button.setOnClickListener {
            val fragment = UploadFragment()
            supportFragmentManager.beginTransaction()
                .addToBackStack(fragment.tag)
                .replace(R.id.posts_frame,fragment)
                .commit()
        }
    }

}