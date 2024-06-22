package com.amaru.sandboxy
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.amaru.sandboxy.databinding.LayoutBinding

private lateinit var binding : LayoutBinding
private lateinit var EditText1 : EditText
private lateinit var EditText2 :EditText
private lateinit var Button1 : Button
private lateinit var TextView1 : TextView



class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = LayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Button1 = findViewById(R.id.Button1)
        EditText1 = findViewById(R.id.EditText1)
        EditText2 = findViewById(R.id.EditText2)
        TextView1 = findViewById(R.id.TextView1)

        Button1.setOnClickListener {
            val text1 = EditText1.text.toString()
            val text2 = EditText2.text.toString()
            binding.TextView1.text = text1






        }

    }
}

