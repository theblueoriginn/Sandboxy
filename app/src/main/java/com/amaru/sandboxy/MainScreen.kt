package com.amaru.sandboxy

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.content.Intent
import android.widget.Toast

private lateinit var editText1: EditText
private lateinit var editText2: EditText
private lateinit var editText3: EditText
private lateinit var button1: Button

private lateinit var textView5: TextView
private lateinit var textView6: TextView

class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        button1 = findViewById(R.id.Button1)
        editText1 = findViewById(R.id.EditText1)
        editText2 = findViewById(R.id.EditText2)
        editText3 = findViewById(R.id.EditText3)
        textView5 = findViewById(R.id.TextView5)
        textView6 = findViewById(R.id.TextView6)
        val userService = UserService()

        button1.setOnClickListener {
            // Example userID, replace with actual data as needed
            val email = editText1.text.toString()
            val password = editText2.text.toString()
            val username = editText3.text.toString() // Example username, replace with actual data as needed

            // Validate input validity
            if (validateInput(email, password)) {
                // Initialize user structure and send the post request
                val user = User(email, password, username)
                userService.addUser(user) { errorMessage ->
                    errorMessage?.let {
                        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    } ?: run {
                        val intent = Intent(this, VerifyMail::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                    }
                }
            }
        }
        textView5.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.length < 6) {
            Toast.makeText(this, "Password must be longer than 5 characters", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
