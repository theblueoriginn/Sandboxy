package com.amaru.sandboxy

import android.os.Bundle
import android.util.Log
import android.app.Activity
import android.widget.TextView
import android.view.View

class HomePage : Activity() {

    private val userService = UserService()
    lateinit var textView0: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        val localUserID = (MyApp.instance as MyApp).globText.toString().toInt() // Convert to Int
        var textView0 = findViewById<TextView>(R.id.TextView0)
        userService.getInfo(localUserID, { userName ->
            println("User Name: $userName")
            (MyApp.instance as MyApp).globName = userName
            textView0.text = userName
        }, { error ->
            println("Failure: ${error?.message}")
        })
    }
}
