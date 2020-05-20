package com.example.platform_univ.modulo.login.mvp

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface RegisterMVP{

    interface View{
        fun showProgres()
        fun hideProgres()
        fun showToask(message:String)
        fun registerSuccess()
        fun registerError()
    }

    interface Presenter{
        fun registerUser(name: String, pass: String, ape: String, phone: String, email: String)
    }

    interface Model{
        fun registerUser(user:String, password:String): Task<AuthResult>
    }

}
