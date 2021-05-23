package com.example.notescollector.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notescollector.R
import com.example.notescollector.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.upload_fragment_layout.*

class UploadFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.upload_fragment_layout,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upload_imageview.setOnClickListener(){

            val currentUser = FirebaseAuth.getInstance().currentUser.toString()
            val subject =subject_edittext.text.toString().trim()
            val noteContent = note_edittext.text.toString().trim()

            val post = Post(currentUser,subject,noteContent)

            val ref = FirebaseDatabase.getInstance().reference.child("NotePosts")
            val key = ref.push().key?:""
            ref.child(key).setValue(post)
            Toast.makeText(requireContext(), "Success!!", Toast.LENGTH_SHORT).show()
        }

    }

}