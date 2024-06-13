package com.amaru.sandboxy
import android.app.Activity as act
import android.content.Intent
import android.os.Bundle

class ActivityScratch : act(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val txtMsg = "abc"

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, txtMsg)
        }
        startActivity(sendIntent)
    }
}
