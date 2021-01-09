package com.example.carrentalservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Details_22go : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_22go)
        val return_button=findViewById<Button>(R.id.return_button)
        return_button.setOnClickListener {
            val intent = Intent(this,ChooseOptionActivity::class.java)
            startActivity(intent)
        }
    }
}