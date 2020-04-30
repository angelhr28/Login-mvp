package com.example.platform_univ.login.presenter

import com.example.platform_univ.login.model.LoginModel
import com.example.platform_univ.login.mvp.LoginMVP
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter( private val view: LoginMVP.View ): LoginMVP.Presenter {

    private val model = LoginModel(this)

    override fun logIn(user: String, password: String) {

        if (user.isEmpty()){
            view.showToask("Ingresa un usuario.")
            return
        }

        if (password.isEmpty()){
            view.showToask("Ingresa una contraseña.")
            return
        }

        view.showProgres()

        model.logIn(user, password).addOnCompleteListener {
            if(it.isSuccessful){
                view.logInSuccess()
            } else {
              view.logInError()
              view.showToask("Usuarion o contraseña incorrecta .")
            }
            view.hideProgres()
        }

    }

    override fun onDestroy() {
        model.auth.signOut()
    }

}