package com.example.platform_univ.modulo.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.platform_univ.R
import com.example.platform_univ.modulo.login.mvp.LoginMVP
import com.example.platform_univ.modulo.login.presenter.LoginPresenter
import com.example.platform_univ.modulo.principal.view.PrincipalActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_AUTH

class LoginActivity : AppCompatActivity(), LoginMVP.View {

    private lateinit var contEdtUser   : TextInputLayout
    private lateinit var contEdtPass   : TextInputLayout
    private lateinit var edtUser       : TextInputEditText
    private lateinit var edtPass       : TextInputEditText
    private lateinit var btnLogin      : Button
    private lateinit var lblCreateUser : TextView
    private lateinit var presenter     : LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contEdtUser   = cont_edt_user
        edtUser       = edt_user
        contEdtPass   = cont_edt_pass
        edtPass       = edt_pass
        btnLogin      = btn_login
        lblCreateUser = lbl_create_user

        presenter = LoginPresenter(this)

        btnLogin.setOnClickListener {
            presenter.logIn(edtUser.text?.trim().toString(), edtPass.text?.trim().toString())
        }

        lblCreateUser.setOnClickListener {
            val intent = Intent(this,  RegisterUserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun showProgres() {}

    override fun hideProgres() {}

    override fun showToask(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()}

    override fun logInSuccess() {
        val intent = Intent(this,  PrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun logInError() {
        edtUser.setText("")
        edtPass.setText("")
        edtUser.clearFocus()
        edtPass.clearFocus()
    }

    override fun onStart() {
        super.onStart()
        if (FIREBASE_AUTH.currentUser != null){
            val intent = Intent(this,  PrincipalActivity::class.java)
            startActivity(intent)
            finish()
        }
        
    }

}
