package com.example.platform_univ.modulo.principal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.platform_univ.R
import com.example.platform_univ.modulo.login.view.LoginActivity
import com.example.platform_univ.root.preferences
import kotlinx.android.synthetic.main.activity_perfil.*
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_AUTH

class PrincipalActivity : AppCompatActivity() {

    private var lblName     : TextView? = null
    private var lblApellido : TextView? = null
    private var lblCorreo   : TextView? = null
    private var lblPhone    : TextView? = null
    private var btnSingout  : Button?   = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        lblName     = lbl_name
        lblApellido = lbl_apellido
        lblCorreo   = lbl_correo
        lblPhone    = lbl_phone
        btnSingout  = btn_singout

        lblName?.text     = preferences.nombre
        lblApellido?.text = preferences.apellido
        lblCorreo?.text   = preferences.correo
        lblPhone?.text    = preferences.phone

        btnSingout?.setOnClickListener {
            FIREBASE_AUTH.signOut()
            preferences.clear()
            val intent = Intent(this,  LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    

}
