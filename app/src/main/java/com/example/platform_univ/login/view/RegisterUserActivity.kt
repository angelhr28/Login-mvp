package com.example.platform_univ.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.platform_univ.R
import com.example.platform_univ.login.presenter.LoginPresenter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity() {

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

    }
}
