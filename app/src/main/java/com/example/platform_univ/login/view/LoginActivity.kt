package com.example.platform_univ.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.platform_univ.R
import com.example.platform_univ.login.mvp.LoginMVP
import com.example.platform_univ.login.presenter.LoginPresenter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

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
            val intent = Intent(this,  PerfilActivity::class.java)
            startActivity(intent)
        }
    }

    override fun showProgres() {}

    override fun hideProgres() {}

    override fun showToask(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()}

    override fun logInSuccess() {
        val intent = Intent(this,  PerfilActivity::class.java)
        startActivity(intent)
    }

    override fun logInError() {
        edtUser.setText("")
        edtPass.setText("")
        edtUser.clearFocus()
        edtPass.clearFocus()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
