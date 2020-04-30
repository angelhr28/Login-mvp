package com.example.platform_univ.login.model

import com.example.platform_univ.login.mvp.LoginMVP
import com.example.platform_univ.login.presenter.LoginPresenter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginModel(private val presenter: LoginPresenter) : LoginMVP.Model {

    internal val auth= FirebaseAuth.getInstance()

    override fun logIn(user: String, password: String) : Task<AuthResult> {
        return auth.signInWithEmailAndPassword(user, password)
    }


}