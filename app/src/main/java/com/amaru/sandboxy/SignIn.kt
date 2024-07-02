package com.amaru.sandboxy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.view.View
import android.os.Handler



class SignIn : AppCompatActivity(), CallBackSıgnIn {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var button1: Button
    private lateinit var textView4: TextView
    private lateinit var textView0: TextView
    private val userService = UserService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signin)

        button1 = findViewById(R.id.Button1)
        editText1 = findViewById(R.id.EditText1)
        editText2 = findViewById(R.id.EditText2)
        textView4 = findViewById(R.id.TextView4)
        textView0 = findViewById(R.id.TextView0)




        button1.setOnClickListener {

            val emailOrUsername = editText1.text.toString()
            val password = editText2.text.toString()
            userService.signIn(emailOrUsername, password, this@SignIn)
        }

        textView4.setOnClickListener {
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }
    }
    val handler = Handler()

    override fun onSuccess(message: String) {
        // Handle successful sign-in
        // For example, navigate to another activity


        val runnable = Runnable {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)

        }
        textView0.visibility = View.VISIBLE
        textView0.text = "You Logged In Successfully..."

        val emailOrUsername = editText1.text.toString()
        userService.getUserID(emailOrUsername, { userID ->
            println("User ID: $userID")
            (MyApp.instance as MyApp).globText = userID

        }, { error ->
            println("Failure: ${error?.message}")
        })

        handler.postDelayed(runnable, 3000)

    }

    override fun onFailure(message: String) {
        // Handle sign-in failure
        // For example, show an error message
        // You can use Toast or Snackbar to display the error
    }
}
