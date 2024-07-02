package com.amaru.sandboxy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

private lateinit var editText1: EditText
private lateinit var button1: Button

class VerifyMail : Activity(), CallBackSıgnIn {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.verifymail)

        button1 = findViewById(R.id.Button1)
        editText1 = findViewById(R.id.EditText1)

        val email = intent.getStringExtra("email")

        button1.setOnClickListener {
            val verifCode = editText1.text.toString()
            if (email != null) {
                val userService = UserService()
                userService.verifyMail(email, verifCode, this@VerifyMail)
            } else {
                Toast.makeText(this, "Email is missing", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val handler = Handler()

    override fun onSuccess(message: String) {
        val runnable = Runnable {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        Toast.makeText(this, "E-mail Successfully Verified!", Toast.LENGTH_SHORT).show()
        handler.postDelayed(runnable, 3000)
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, "Verification failed: $message", Toast.LENGTH_SHORT).show()
    }
}
