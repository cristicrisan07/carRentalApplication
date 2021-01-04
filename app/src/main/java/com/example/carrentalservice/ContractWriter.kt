package com.example.carrentalservice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray
import org.json.JSONObject

class ContractWriter : AppCompatActivity() {
    var resultIntent=Intent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract_writer)



       val rentBtn=findViewById<Button>(R.id.rentBtn)
        rentBtn.setOnClickListener {

            val vin=this.intent.extras!!.get("VIN").toString()
            val id= this.intent.extras!!.get("user_id").toString()
            val logUrl = "http://34.107.31.239/contractUpload.php"
            val stringRequest: StringRequest = object : StringRequest(Method.POST,
                    logUrl,
                    {
                        Toast.makeText(applicationContext,"You have successfully rented this car",Toast.LENGTH_LONG).show()

                        resultIntent.putExtra("code","carWasRented")
                        setResult(Activity.RESULT_OK,resultIntent)
                    this.finish()
                    },
                    { _ ->

                        Toast.makeText(this, "An error occured. Check rental log!", Toast.LENGTH_LONG).show()
                        resultIntent.putExtra("code","ERROR")
                        setResult(Activity.RESULT_CANCELED,resultIntent)
                    }) {

                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["VIN"] =vin
                    params["idClient"]=id
                    return params
                }

            }
            MySingleton.MySingleton.getInstance(this).addToRequestQueue(stringRequest)
        }


    }

}