package com.example.notescollector.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notescollector.R
import com.example.notescollector.model.SignupUser
import kotlinx.android.synthetic.main.signin_activity.*
import kotlinx.android.synthetic.main.signin_activity.signup_button
import kotlinx.android.synthetic.main.signup_fragment_layout.*

class SignUpFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.signup_fragment_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signup_button.setOnClickListener {
            val email = su_email_edittext.text.toString().trim()
            val password = su_password_edittext.text.toString().trim()

            (requireActivity() as SignInActivity).signupUser(SignupUser(email,password))
        }

    }

}