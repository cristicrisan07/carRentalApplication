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
import com.example.carrentalservice.R

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButton=findViewById<Button>(R.id.button2)
        val myTxtLastName=findViewById<TextView>(R.id.last_name)
        val myTxtFirstName=findViewById<TextView>(R.id.firstName)
        val myTxtPhone=findViewById<TextView>(R.id.phone_text)
        val myTxtPassword=findViewById<TextView>(R.id.password_text)
        val myTxtCNP=findViewById<TextView>(R.id.cnp_text)
        val myTxtAddress=findViewById<TextView>(R.id.address_text)
        val myTxtDriverLicense=findViewById<TextView>(R.id.expirationtime_text)

        myButton.setOnClickListener {
            Validator.isValidName(myTxtLastName,true)
            Validator.isValidName(myTxtFirstName,true)
            Validator.isValidPassword(myTxtPassword,true)
            Validator.isValidPhone(myTxtPhone,true)
            Validator.isValidCNP(myTxtCNP,true)
            //Validator.isValidDriverLicense(myTxtDriverLicense,true)
            Validator.isValidAddres(myTxtAddress,true)
            if(Validator.isValidName(myTxtLastName,true)==true && Validator.isValidName(myTxtFirstName,true) &&  Validator.isValidPassword(myTxtPassword,true)
                    && Validator.isValidPhone(myTxtPhone,true) && Validator.isValidCNP(myTxtCNP,true) && Validator.isValidAddres(myTxtAddress,true))
            {
                val  intent=  Intent(this,SecondActivity::class.java)
                startActivity(intent)
                Toast.makeText(this,"The data has been saved",Toast.LENGTH_LONG).show();
            }
            myTxtLastName.text=" "
            myTxtFirstName.text=" "
            myTxtPhone.text=" "
            myTxtPassword.text=" "
            myTxtCNP.text=" "
            myTxtAddress.text=" "
            myTxtDriverLicense.text=" "
        }
    }



}