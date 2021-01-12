package com.example.carrentalservice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.toolbox.StringRequest

class ChooseOptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_option)
        val btn_22go=findViewById<RadioButton>(R.id.btn_22go)
        val btn_72go=findViewById<RadioButton>(R.id.btn_72go)
        val btn_honey=findViewById<RadioButton>(R.id.btn_honeyMoon)
        val get_button=findViewById<Button>(R.id.get_btn)
        get_button.setOnClickListener {
            if(!btn_22go.isChecked && !btn_72go.isChecked && !btn_honey.isChecked)
                    Toast.makeText(applicationContext,"You have to choose a subscription",Toast.LENGTH_LONG).show()
           var type:String=""
            if(btn_22go.isChecked ) {
                type="22go"
            }
                else{
             if(btn_72go.isChecked){
                  type="72go"
            }
         else{
               if(btn_honey.isChecked){
               type="Honeymoon"
              }
                 }
                }

            val uID = this.intent.extras!!.get("uID").toString()
            val logUrl = "http://34.107.31.239/Subscribe.php"
            val stringRequest: StringRequest = object : StringRequest(Method.POST,
                    logUrl,
                    { response ->
                        Toast.makeText(applicationContext, "Your have successfully subscribed!", Toast.LENGTH_LONG).show()
                        this.intent.putExtra("type",type)
                        setResult(Activity.RESULT_OK,this.intent)
                        this.finish()
                    },
                    { _ ->
                        Toast.makeText(this, "Could not subscribe!", Toast.LENGTH_LONG).show()
                    }) {

                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["Type"] =type
                    params["idClient"]=uID
                    return params
                }

            }
            MySingleton.MySingleton.getInstance(this).addToRequestQueue(stringRequest)

        }

    }
}