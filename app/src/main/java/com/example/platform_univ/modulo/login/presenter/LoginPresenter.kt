package com.example.platform_univ.modulo.login.presenter

import com.example.platform_univ.modulo.login.model.LoginModel
import com.example.platform_univ.modulo.login.mvp.LoginMVP
import com.example.platform_univ.root.preferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_AUTH
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_DB

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
                val dataBaseUser = FIREBASE_DB.reference
                preferences.deviceToken = FIREBASE_AUTH.currentUser?.uid
                dataBaseUser.child("Usuario")
                            .child(preferences.deviceToken ?: "")
                            .addValueEventListener( object : ValueEventListener {
                                    override fun onCancelled(p0: DatabaseError) {
                                        view.logInError()
                                        view.showToask("El usuario no existe.")
                                        view.hideProgres()
                                    }
                                    override fun onDataChange(result: DataSnapshot) {
                                        if(result.exists()){
                                            preferences.apply {
                                                this.nombre   = result.child("nombres").getValue().toString()
                                                this.phone    = result.child("phone").getValue().toString()
                                                this.correo   = result.child("correo").getValue().toString()
                                                this.apellido = result.child("apellidos").getValue().toString()
                                                this.password = result.child("password").getValue().toString()
                                            }
                                        }
                                        view.logInSuccess()
                                        view.hideProgres()
                                    }
                                }
                            )
            } else {
                view.logInError()
                view.showToask("Usuarion o contraseña incorrecta .")
                view.hideProgres()
            }
        }

    }
}
