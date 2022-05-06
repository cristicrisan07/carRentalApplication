package com.example.carrentalservice.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.carrentalservice.R


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_first)
        val buttonReg=findViewById<Button>(R.id.button_reg)
        val buttonSearch=findViewById<ImageButton>(R.id.searchImg)
        val buttonLogin=findViewById<Button>(R.id.button_login)
        buttonReg.setOnClickListener {
            val  intent=  Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        buttonSearch.setOnClickListener {
            val  intent=  Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
        buttonLogin.setOnClickListener {
            val  intent=  Intent(this, LoginActivity::class.java)
           startActivity(intent)
        }
    }
}