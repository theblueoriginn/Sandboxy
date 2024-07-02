package com.amaru.sandboxy
import android.app.Application
// MyApp.kt
class MyApp : Application() {
    var globText: String? = ""
    var globName: String? = ""

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}