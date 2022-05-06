package com.example.carrentalservice.Service

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.toolbox.StringRequest

class ContractWriter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.intent.putExtra("code","mindChange")

        setResult(Activity.RESULT_CANCELED,this.intent)


            val vin=this.intent.extras!!.get("VIN").toString()
            val id= this.intent.extras!!.get("user_id").toString()
            val logUrl = "http://10.0.2.2:8080/uploads/contractUpload.php"
            val stringRequest: StringRequest = object : StringRequest(Method.POST,
                    logUrl,
                    {
                        Toast.makeText(applicationContext,"You have successfully rented this car",Toast.LENGTH_LONG).show()


                        this.intent.putExtra("code","carWasRented")
                          setResult(Activity.RESULT_OK,this.intent)
                    this.finish()
                    },
                    { _ ->

                        Toast.makeText(this, "An error occured. Check rental log!", Toast.LENGTH_LONG).show()

                        this.intent.putExtra("code","ERROR")
                        setResult(Activity.RESULT_CANCELED,this.intent)
                        this.finish()
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