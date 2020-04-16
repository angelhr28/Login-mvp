package com.example.platform_univ.helpers.application

import android.content.Context

/**
 * Created by Sebastian on 21/02/2018.
 */
class MySharedPreferences(context: Context) {

    private val fileName = "smiledu_preferences"

    private val prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    var deviceToken: String?
        get() = prefs.getString("device_token", "")
        set(value) = prefs.edit().putString("device_token", value).apply()



    fun clear() {
        prefs.edit().remove("flg_rol_adicional").apply()

    }

    fun clearPost(){
        prefs.edit().remove("desc_banner").apply()
        prefs.edit().remove("flg_error_sunat").apply()
        prefs.edit().remove("titulo_banner").apply()
        prefs.edit().remove("img_banner").apply()
        prefs.edit().remove("ruta_post").apply()
    }

    fun clearYear() {
        prefs.edit().remove("year").apply()
    }

    fun removePreference(key: String) {
        prefs.edit().remove(key).apply()
    }
}