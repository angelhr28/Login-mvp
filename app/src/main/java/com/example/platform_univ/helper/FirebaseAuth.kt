package com.example.platform_univ.helper

import com.google.firebase.auth.FirebaseAuth
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_AUTH

class Auth private constructor() {

    companion object {
    private var instance: FirebaseAuth? = null
        fun getInstance(): FirebaseAuth {
            if (instance == null) instance = FIREBASE_AUTH
            return instance!!
        }
    }
}
