package com.example.payboxbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pbbutton.pbbuttonlibrary.PayboxButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val payboxButton = findViewById<PayboxButton>(R.id.paybox_btn)
        payboxButton.onPayboxClickListener {
            payboxButton.setAmount(5.0)
        }
    }
}