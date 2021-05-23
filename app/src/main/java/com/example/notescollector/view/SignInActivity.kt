package com.example.notescollector.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.notescollector.R
import com.example.notescollector.model.SignupUser
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signin_activity.*

//SIGN IN PAGE

class SignInActivity : AppCompatActivity() {

    private val signupFragment=SignUpFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_activity)

        //sign in
        signin_button.setOnClickListener {
            //get email and password from edittext
            val email = email_edittext.text.toString().trim()
            val password = password_edittext.text.toString().trim()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener { completion->

                //check if email and password is correct
                if(completion.isSuccessful){

                    //check if user has verified their email
                    if(FirebaseAuth.getInstance().currentUser?.isEmailVerified == true){
                        //open homepage
                        openHomePage()
                    }
                    //not verified
                    else{
                        verifyPromptMessage(email)
                    }
                }
                //not successful
                else{
                    Log.d("TAG_X","incorrect email or password")
                    showError(completion)
                }
            }
        }

        //signup button
        signup_button.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right
                )
                .addToBackStack(signupFragment.tag)
                .replace(R.id.signup_frame,signupFragment)
                .commit()
        }
    }

    fun signupUser(user: SignupUser) {
        //sign up new user using data sent from sig up fragment
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(user.email,user.password)
            .addOnCompleteListener { completion->

                //if sign up is successful
                if(completion.isSuccessful){
                    openHomePage()
                }
                else{
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                    verifyPromptMessage(user.email)
                }

            }
    }

    private fun showError(completion: Task<AuthResult>) {
       Toast.makeText(this,completion.toString(),Toast.LENGTH_LONG)
    }

    private fun verifyPromptMessage(user: String) {
        AlertDialog.Builder(
            ContextThemeWrapper(
                this,
                R.style.Theme_AppCompat_Dialog
            )
        )
            .setTitle("Confirmation Email Sent")
            .setMessage("Please check your email: ${user}. A confirmation email has been sent.")
            .setPositiveButton("Okay") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }

    private fun openHomePage() {
        startActivity(Intent(this,HomePageActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }
}