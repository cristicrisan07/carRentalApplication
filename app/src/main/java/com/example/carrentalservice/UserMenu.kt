package com.example.carrentalservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import java.sql.Date
import kotlin.properties.Delegates
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
        subsbtn.setOnClickListener {
            val intent =Intent(this,ChooseOptionActivity::class.java)
            intent.putExtra("uID",userID.toString())
            startActivity(intent)
        }
        val endBtn=findViewById<Button>(R.id.endBtn)
        endBtn.setOnClickListener {
             val delivered=Intent(this,MarkAsDelivered::class.java)
            delivered.putExtra("map",this.intent.extras!!.get("map") as ArrayList<*>)
            startActivityForResult(delivered, LAUNCH_MARK_AS_DELIVERED)
        }
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
            var priceUntilNow:String=(pricePerKm.toDouble() * rand).toString()

            if(userStatus.getJSONObject(0).getString("subStatus")!="notSubscribed")
            {
                priceUntilNow=""
            }


            contrViewText="You are driving:\n $manufacturer $model from $prodYear.\n " +
                    "You started your ride from: $departureLocation.\n" +
                    "Battery level: $batteryLevel% \n " +
                    "You have to pay $priceUntilNow euros until now."
        }
        subView.text=subViewText
        contrView.text=contrViewText


    }
    companion object {

        private const val LAUNCH_MARK_AS_DELIVERED = 4
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if(requestCode== LAUNCH_MARK_AS_DELIVERED){
        if(resultCode==RESULT_OK){
            Toast.makeText(applicationContext,"Thanks for choosing our services!",Toast.LENGTH_LONG).show()
        }
        else{

        }
    }
        else{

        }
    }
}