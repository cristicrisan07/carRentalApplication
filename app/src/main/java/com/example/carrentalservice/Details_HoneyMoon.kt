package com.example.carrentalservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.carrentalservice.ChooseOptionActivity

class Details_HoneyMoon : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details__honey_moon)
        val return_button=findViewById<Button>(R.id.return_butoon)
        return_button.setOnClickListener {
            val intent = Intent(this, ChooseOptionActivity::class.java)
            startActivity(intent)
        }
    }
}