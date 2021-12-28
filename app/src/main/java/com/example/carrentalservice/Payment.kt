package com.example.carrentalservice

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.toolbox.StringRequest
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
                status = if (chance < 2)
                    PaymentStatus.FAILURE
                else PaymentStatus.SUCCESS
                if (status == PaymentStatus.SUCCESS) {
                    Toast.makeText(this, "Payment Successful", Toast.LENGTH_LONG).show()
                    val price =this.intent.extras!!.get("price").toString()
                    val uID = this.intent.extras!!.get("uID").toString()
                    val VIN = this.intent.extras!!.get("VIN").toString()
                    val location=this.intent.extras!!.get("location").toString()
                    val logUrl = "http://10.0.2.2:8080/uploads/Park.php"
                    val stringRequest: StringRequest = object : StringRequest(
                        Method.POST,
                        logUrl,
                        {response ->
                            if(response=="Delivered!")
                            {setResult(Activity.RESULT_OK)}
                            else{
                                if(response=="No such address!")
                                {
                                    Toast.makeText(applicationContext,"No such address PHP error; contact support!",Toast.LENGTH_LONG).show()
                                    setResult(Activity.RESULT_CANCELED)
                                }
                            }
                            this.finish()
                        },
                        {
                            Toast.makeText(this, "Could not subscribe!", Toast.LENGTH_LONG).show()
                        }) {

                        override fun getParams(): MutableMap<String, String> {
                            val params = HashMap<String, String>()
                            params["idClient"]=uID
                            params["location"]=location
                            params["vin"]=VIN
                            params["amount"]=price
                            return params
                        }

                    }
                    MySingleton.MySingleton.getInstance(this).addToRequestQueue(stringRequest)
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