package com.example.platform_univ.modulo.login.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.platform_univ.R
import com.example.platform_univ.modulo.login.mvp.RegisterMVP
import com.example.platform_univ.modulo.login.presenter.RegisterPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_register_user.*
import pe.softhy.smiledu.helper.application.Constants.FIREBASE_STORAGE


class RegisterUserActivity : AppCompatActivity(), RegisterMVP.View {

    private val TAG  = this.javaClass.toString()
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
    private lateinit var btnImag      : FloatingActionButton
    private lateinit var imagPhoto    : ImageView
    private lateinit var btnRegist    : Button
    private lateinit var presenter    : RegisterMVP.Presenter
    private lateinit var strorageRef  : StorageReference
    private lateinit var uriImage     : Uri
    val strorageRefP = FIREBASE_STORAGE.getReference("images")

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
        btnImag      = btn_add_img
        imagPhoto    = img_foto_regist

        presenter = RegisterPresenter(this)
        strorageRef = FIREBASE_STORAGE.reference
        btnRegist.setOnClickListener {
            val name   = edtName.text?.trim().toString()
            val pass   = edtPass.text?.trim().toString()
            val ape    = edtApe.text?.trim().toString()
            val phone  = edtPhone.text?.trim().toString()
            val email  = edtEmail.text?.trim().toString()
            presenter.registerUser(name,pass,ape,phone,email)
        }

        btnImag.setOnClickListener {
            fileChooser()
        }

    }


    fun getExtension (uri: Uri): String? {
        val cr = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return  mimeTypeMap.getMimeTypeFromExtension(cr.getType(uri))
    }
    fun getMimeType(url: String?): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }
    private fun fileUploader() {
        Log.e(TAG,"meraa :: ${getExtension(uriImage)}")
        val ref = strorageRefP.child("${System.currentTimeMillis()}.${getExtension(uriImage)}"  )

        ref.putFile(uriImage)
            .addOnSuccessListener { taskSnapshot -> // Get a URL to the uploaded content
                val downloadUrl: String = taskSnapshot.storage.downloadUrl.toString()
                Log.e(TAG,"holaaa $downloadUrl")
                showToask("se subio revisa prro :v")
            }
    }

    private fun fileChooser(){
        val intent = Intent()
        intent.apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            data.data?.let {
                uriImage = it
                imagPhoto.setImageURI(uriImage)
            }

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
        fileUploader()


//        val intent = Intent(this,  PrincipalActivity::class.java)
//        startActivity(intent)
//        finish()
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
