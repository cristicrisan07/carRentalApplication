package com.example.carrentalservice.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.example.carrentalservice.Service.MySingleton
import com.example.carrentalservice.R
import org.json.JSONArray

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn=findViewById<Button>(R.id.loginBtn)
        val dontHaveAcc=findViewById<TextView>(R.id.noAccTxt)
        dontHaveAcc.setOnClickListener {
            val intent =Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        loginBtn.setOnClickListener {
            val username=findViewById<TextView>(R.id.userInsert)
            val password=findViewById<TextView>(R.id.passInsert)


            val logUrl="http://10.0.2.2:8080/uploads/login.php"
            val stringRequest: StringRequest = object: StringRequest(Method.POST,
                    logUrl,
                    { response->

                        val jsonarr= JSONArray(response)
                        val jsonobj=jsonarr.getJSONObject(0)
                        if(jsonobj.getString("code")=="logged_in") {
                        val firstName=jsonobj.getString("first_name")
                        val lastName=jsonobj.getString("last_name")
                        val userID=jsonobj.getString("user_id")
                        val isAdmin=jsonobj.getString("isAdmin")

                                Toast.makeText(this@LoginActivity,"Welcome $firstName $lastName",Toast.LENGTH_LONG).show()
                                   val intent= Intent(this, MapsActivity::class.java)
                            intent.putExtra("user_id", userID)
                            intent.putExtra("firstName",firstName)
                            intent.putExtra("lastName",lastName)
                            intent.putExtra("isAdmin",isAdmin)
                            startActivity(intent)
                        }
                        else{
                            if(jsonobj.getString("code")=="login_failed")
                            {
                                Toast.makeText(this@LoginActivity,"Incorrect username or password!",Toast.LENGTH_LONG).show()
                                username.text = ""
                                password.text= ""
                            }
                        }

                    },
                    {error->

                        Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
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