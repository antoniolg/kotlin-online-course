package com.antonioleiva.myplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.message)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                toast("Hello ${textView.text}")
            }
        })
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}