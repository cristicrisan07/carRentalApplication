package com.example.carrentalservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginBtn=findViewById<Button>(R.id.loginBtn)
        loginBtn.setOnClickListener {
            val username=findViewById<TextView>(R.id.userInsert)
            val password=findViewById<TextView>(R.id.passInsert)


            val logUrl="http://34.107.31.239/login.php"
            val stringRequest: StringRequest = object: StringRequest(Method.POST,
                    logUrl,
                    { response->
                        val jsonarr= JSONArray(response)
                        val jsonobj=jsonarr.getJSONObject(0)
                        val firstName=jsonobj.getString("first_name")
                        val lastName=jsonobj.getString("last_name")
                        if(jsonobj.getString("code")=="logged_in") {

                                Toast.makeText(this,"Welcome $firstName $lastName",Toast.LENGTH_LONG).show()
                                   val intent= Intent(this,MapsActivity::class.java)
                            startActivity(intent)
                        }

                    },
                    {error->

                        Toast.makeText(this,"nu a mers",Toast.LENGTH_LONG).show()
                    }){

                override fun getParams(): MutableMap<String, String> {
                    val params=HashMap<String,String>()
                    params["Username"] = username.text.toString()
                    params["Password"] = password.text.toString()


                    return params
                }

            }
            MySingleton.MySingleton.getInstance(this).addToRequestQueue(stringRequest)
        }
    }
}