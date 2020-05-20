package com.example.platform_univ.modulo.login.model

import com.example.platform_univ.modulo.login.mvp.RegisterMVP
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import pe.softhy.smiledu.helper.application.Constants

class RegisterModel(val registerPresenter: RegisterMVP.Presenter): RegisterMVP.Model {

    override fun registerUser(user: String, password: String): Task<AuthResult> {
        return Constants.FIREBASE_AUTH.createUserWithEmailAndPassword(user, password)
    }
}
