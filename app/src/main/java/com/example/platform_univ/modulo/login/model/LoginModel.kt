package com.example.platform_univ.modulo.login.model

import com.example.platform_univ.modulo.login.mvp.LoginMVP
import com.example.platform_univ.modulo.login.presenter.LoginPresenter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_AUTH

class LoginModel(private val presenter: LoginPresenter) : LoginMVP.Model {

    override fun logIn(user: String, password: String) : Task<AuthResult> {
        return FIREBASE_AUTH.signInWithEmailAndPassword(user, password)
    }


}
