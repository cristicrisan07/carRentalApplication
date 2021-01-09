package com.example.carrentalservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ChooseOptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_option)
        val btn_22go=findViewById<RadioButton>(R.id.btn_22go)
        val btn_72go=findViewById<RadioButton>(R.id.btn_72go)
        val btn_honey=findViewById<RadioButton>(R.id.btn_honeyMoon)
        val get_button=findViewById<Button>(R.id.get_btn)
        val txt22go=findViewById<TextView>(R.id.details_22go)
        val txt72go=findViewById<TextView>(R.id.details_72go)
        val txthoneyMoon=findViewById<TextView>(R.id.details_honey)
        txt22go.setOnClickListener {
            val intent = Intent(this,Details_22go::class.java)
            startActivity(intent)
        }
        txt72go.setOnClickListener {
            val intent = Intent(this,Details_72go::class.java)
            startActivity(intent)
        }
        txthoneyMoon.setOnClickListener {
            val intent = Intent(this,Details_HoneyMoon::class.java)
            startActivity(intent)
        }
        get_button.setOnClickListener {
            if(!btn_22go.isChecked && !btn_72go.isChecked && !btn_honey.isChecked)
                    Toast.makeText(applicationContext,"You have to choose a subscription",Toast.LENGTH_LONG).show()
            if(btn_22go.isChecked || btn_72go.isChecked || btn_honey.isChecked)
                Toast.makeText(applicationContext, "Your subscription has been made", Toast.LENGTH_LONG).show()

        }

    }
}