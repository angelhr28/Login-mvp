package com.example.platform_univ.modulo.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.platform_univ.R
import com.example.platform_univ.modulo.login.mvp.RegisterMVP
import com.example.platform_univ.modulo.login.presenter.RegisterPresenter
import com.example.platform_univ.modulo.principal.view.PrincipalActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity(), RegisterMVP.View {

    private lateinit var contEdtName  : TextInputLayout
    private lateinit var contEdtApe   : TextInputLayout
    private lateinit var contEdtPhone : TextInputLayout
    private lateinit var contEdtEmail : TextInputLayout
    private lateinit var contEdtPass  : TextInputLayout
    private lateinit var edtName      : TextInputEditText
    private lateinit var edtApe       : TextInputEditText
    private lateinit var edtPhone     : TextInputEditText
    private lateinit var edtEmail     : TextInputEditText
    private lateinit var edtPass      : TextInputEditText
    private lateinit var btnRegist    : Button
    private lateinit var dataBaseUser : DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var presenter: RegisterMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_user)

        contEdtName  = cont_edt_name_user
        contEdtApe   = cont_edt_ape_user
        contEdtPhone = cont_edt_phone_user
        contEdtEmail = cont_edt_email_user
        contEdtPass  = cont_edt_pass_user
        edtName      = edt_name_user
        edtApe       = edt_ape_user
        edtPhone     = edt_phone_user
        edtEmail     = edt_email_user
        edtPass      = edt_pass_user
        btnRegist    = btn_register

        presenter = RegisterPresenter(this)

        btnRegist.setOnClickListener {
            val name   = edtName.text?.trim().toString()
            val pass   = edtPass.text?.trim().toString()
            val ape    = edtApe.text?.trim().toString()
            val phone  = edtPhone.text?.trim().toString()
            val email  = edtEmail.text?.trim().toString()
            presenter.registerUser(name,pass,ape,phone,email)
        }
    }

    override fun showProgres() {
    }

    override fun hideProgres() {
    }

    override fun showToask(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun registerSuccess() {
        val intent = Intent(this,  PrincipalActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun registerError() {
        edtName.setText("")
        edtPass.setText("")
        edtApe.setText("")
        edtPhone.setText("")
        edtEmail.setText("")
        edtName.clearFocus()
        edtPass.clearFocus()
        edtApe.clearFocus()
        edtPhone.clearFocus()
        edtEmail.clearFocus()
    }
}
