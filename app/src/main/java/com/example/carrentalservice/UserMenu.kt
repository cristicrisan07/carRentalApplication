package com.example.carrentalservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.TextView
import org.json.JSONArray
import java.sql.Date
import kotlin.random.Random

class UserMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_menu)
        val userID=this.intent.extras!!.get("user_id")
        val fullname:String=this.intent.extras!!.get("firstName").toString()+" "+this.intent.extras!!.get("lastName").toString()
        val tv1=findViewById<TextView>(R.id.tw1)
        tv1.text=fullname
        val userStatus=JSONArray(this.intent.extras!!.get("serverResponse").toString())
        val subsbtn=findViewById<Button>(R.id.subButton)
        var subViewText:String?=null
        val subView=findViewById<TextView>(R.id.tw2)
        val contrView=findViewById<TextView>(R.id.tw3)
        var contrViewText:String?=null
        if(userStatus.getJSONObject(0).getString("subStatus")=="notSubscribed")
        {
        subViewText="No active subscription. \nPress the button on the right to see the offers -->>"
        }
        else{

            subsbtn.visibility=INVISIBLE
            val type=userStatus.getJSONObject(0).getString("type")
            val expDate=userStatus.getJSONObject(0).getString("expirationDate")
            subViewText= "You have a $type subscription active until $expDate"
        }
        val endBtn=findViewById<Button>(R.id.endBtn)
        if(userStatus.getJSONObject(1).getString("agreeStatus")=="nocarRented"){
            endBtn.visibility= INVISIBLE
            contrViewText="No car rented."
            contrView.text=contrViewText
        }
        else{
            var carInfo=userStatus.getJSONObject(3)
            val VIN=carInfo.getString("VIN")
            val prodYear=carInfo.getString("Year")
            val pricePerKm=carInfo.getString("Price")
            val batteryLevel=carInfo.getString("Battery")
            carInfo=userStatus.getJSONObject(4)
            val manufacturer=carInfo.getString("manufacturer")
            val model=carInfo.getString("model")
            carInfo=userStatus.getJSONObject(2)
            val departureLocation=carInfo.getString("departure")
            val rand= Random.nextInt(2,100)
            val priceUntilNow=pricePerKm.toDouble() * rand
            contrViewText="You are driving:\n $manufacturer $model from $prodYear.\n " +
                    "You started your ride from: $departureLocation.\n" +
                    "Battery level: $batteryLevel% \n " +
                    "You have to pay $priceUntilNow euros until now."
        }
        subView.text=subViewText
        contrView.text=contrViewText


    }
}