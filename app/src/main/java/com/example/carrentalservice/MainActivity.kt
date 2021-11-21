package com.example.carrentalservice

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.toolbox.StringRequest
import com.example.carrentalservice.MySingleton.MySingleton.Companion.getInstance
import com.example.carrentalservice.R
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButton=findViewById<Button>(R.id.button2)
        val myTxtLastName=findViewById<TextView>(R.id.last_name)
        val myTxtFirstName=findViewById<TextView>(R.id.firstName)
        val myTxtPassword=findViewById<TextView>(R.id.password_text)
        val myTxtCNP=findViewById<TextView>(R.id.cnp_text)
        val myTxtAddress=findViewById<TextView>(R.id.address_text)
        val myTxtDriverLicense=findViewById<TextView>(R.id.expirationtime_text)
        val myTxtEmail=findViewById<TextView>(R.id.email_text)
        val myTxtUsername=findViewById<TextView>(R.id.username_text)
        val haveAcc=findViewById<TextView>(R.id.haveAcc)
        haveAcc.setOnClickListener {
            val intent =Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        myButton.setOnClickListener {
            Validator.isValidName(myTxtLastName,true)
            Validator.isValidName(myTxtFirstName,true)
            Validator.isValidEmail(myTxtEmail,true)
            Validator.isValidAddres(myTxtAddress,true)
            Validator.isValidPassword(myTxtPassword,true)
            Validator.isValidCNP(myTxtCNP,true)
            if(Validator.isValidName(myTxtLastName,true) && Validator.isValidName(myTxtFirstName,true) && Validator.isValidPassword(myTxtPassword,true) && Validator.isValidCNP(myTxtCNP,true) && Validator.isValidAddres(myTxtAddress,true) && Validator.isValidEmail(myTxtEmail,true))
            {
                val reg_url="http://10.0.2.2:8080/uploads/register.php"
                val stringRequest: StringRequest = object: StringRequest(
                    Method.POST,
                    reg_url,
                    { response->
                        val jsonarr= JSONArray(response)
                        val jsonobj=jsonarr.getJSONObject(0)
                        val code=jsonobj.getString("code")
                        val msg=jsonobj.getString("message")
                        Toast.makeText(this, "$code $msg",Toast.LENGTH_LONG).show()
                        if(code!="reg_failed")
                        {
                            val intent =Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }
                    },
                    {


                    }){

                    override fun getParams(): MutableMap<String, String> {
                        val params=HashMap<String,String>()
                        params.put("Last_name",myTxtLastName.text.toString())
                        params.put("First_name",myTxtFirstName.text.toString())
                        params.put("Address",myTxtAddress.text.toString())
                        params.put("Dled",myTxtDriverLicense.text.toString())
                        params.put("Password",myTxtPassword.text.toString())
                        params.put("Email",myTxtEmail.text.toString())
                        params.put("User_name",myTxtUsername.text.toString())
                        params.put("Pic",myTxtCNP.text.toString())
                        return params
                    }

                }

                getInstance(this).addToRequestQueue(stringRequest)

                
            }

        }
    }



}