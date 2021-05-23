package com.example.notescollector.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth

class StartUpChooser : AppCompatActivity() {

    private lateinit var openIntent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        openIntent = if (FirebaseAuth.getInstance().currentUser != null && FirebaseAuth.getInstance().currentUser?.isEmailVerified != false) {
            //Logged in...navigate to home
            Intent(this, HomePageActivity::class.java)
        } else {
            //Not logged in... navigate to login
            Intent(this, SignInActivity::class.java)
        }

        startActivity(openIntent.also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}