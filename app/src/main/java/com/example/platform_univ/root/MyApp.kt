package com.example.platform_univ.root

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.platform_univ.helper.application.MySharedPreferences

val preferences       : MySharedPreferences by lazy { MyApp.prefs!! }
lateinit var ctx      : Context

class MyApp: Application(){

    private val group1 = "Group-01"
    private val group2 = "Group-02"

    companion object {
        var prefs: MySharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        prefs = MySharedPreferences(applicationContext)
        ctx = this
    }
}
