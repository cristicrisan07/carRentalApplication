package com.example.carrentalservice

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import kotlin.random.Random

class Payment : AppCompatActivity() {
     var status =PaymentStatus.FAILURE
    enum class PaymentStatus {
        SUCCESS, FAILURE

    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        var pay=findViewById<Button>(R.id.proceedButton)
        pay.setOnClickListener {

            var valid = true


            if (findViewById<TextView>(R.id.cardnumbertextbox).text.length != 16 || findViewById<TextView>(
                    R.id.CVVTextBox
                ).text.length != 3
            ) {
                valid = false
            }

            var str =
                findViewById<TextView>(R.id.monthTextView).text.toString() + "-20" + findViewById<TextView>(
                    R.id.yearTextview
                ).text.toString()

          if(str.take(2).toInt() in 1..12) {
              if (LocalDateTime.now().year == str.takeLast(4).toInt()) {
                  if (LocalDateTime.now().monthValue < str.take(2).toInt())
                      valid = false
              } else if (LocalDateTime.now().year > str.takeLast(4).toInt()) {
                  valid = false
              }
          }else valid=false

            if (valid) {
                var chance = Random.nextInt(9)
                status = if (chance < 3)
                    PaymentStatus.SUCCESS
                else PaymentStatus.FAILURE
                if (status == PaymentStatus.SUCCESS) {
                    Toast.makeText(this, "Payment Successful", Toast.LENGTH_LONG).show()
                    this.finish()
                } else {
                    Toast.makeText(this, "Payment Error. Retry Payment.", Toast.LENGTH_LONG).show()
                }
            }

            else{
                Toast.makeText(this, "Payment Error. Check your data.", Toast.LENGTH_LONG).show()
            }
        }
    }
}