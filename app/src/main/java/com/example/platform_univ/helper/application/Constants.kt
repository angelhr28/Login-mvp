package pe.softhy.smiledu.helper.application

import android.graphics.Color
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

/**
 * Created by Sebastian on 21/02/2018.
 */
object Constants {
    val FIREBASE_AUTH = FirebaseAuth.getInstance()
    val FIREBASE_DB = FirebaseDatabase.getInstance()
    val FIREBASE_STORAGE = FirebaseStorage.getInstance()
}
