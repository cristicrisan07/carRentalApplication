package com.example.carrentalservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.toolbox.StringRequest
import com.example.carrentalservice.R
import org.json.JSONArray


class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_first)
        val buttonReg=findViewById<Button>(R.id.button_reg)
        val buttonSearch=findViewById<ImageButton>(R.id.searchImg)
        val buttonLogin=findViewById<Button>(R.id.button_login)
        buttonReg.setOnClickListener {
            val  intent=  Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        buttonSearch.setOnClickListener {
            val  intent=  Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }
        buttonLogin.setOnClickListener {
            val  intent=  Intent(this, LoginActivity::class.java)
           startActivity(intent)
        }
    }
}