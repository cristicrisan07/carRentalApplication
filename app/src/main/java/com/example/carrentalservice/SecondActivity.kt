package com.example.carrentalservice

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.NonCancellable.cancel

class SecondActivity : AppCompatActivity() {
    val LAUNCH_CONTRACT_WRITER = 1
    var resultIntent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val helpBtn=findViewById<ImageButton>(R.id.helpBtn)
        helpBtn.setOnClickListener {
            Toast.makeText(applicationContext,"Click on a car to rent it.",Toast.LENGTH_LONG).show()
        }

        var listView = findViewById(R.id.carsView) as ListView
        var arrCar: ArrayList<Cars> = ArrayList()

        val nrOfCars = this.intent.extras!!.get("nrOfCars") as Int
        for (i in 0 until nrOfCars) {

            val title = (this.intent.extras!!.get("manufacturer$i") as String + " " + this.intent.extras!!.get("model$i") as String + " " + this.intent.extras!!.get("Year$i") as String)
            var description:String?=null
            if(this.intent.extras!!.get("isSubscribed")!="notSubscribed") {
                 description = ("Battery level: " + this.intent.extras!!.get("Battery$i") as String + "%\nPrice per km:" + this.intent.extras!!.get("Price$i") as String + "€ ")
            }
            else{
                description = ("Battery level: " + this.intent.extras!!.get("Battery$i") as String)
            }
                var img: Int? = null
            val model = this.intent.extras!!.get("model$i") as String
            if (model == "ForTwo EQ") {
                img = R.drawable.smf2
            } else {
                if (model == "Zoe") {
                    img = R.drawable.zoe

                } else {
                    if (model == "Cooper SE") {
                        img = R.drawable.minise
                    } else {
                        if (model == "eLeaf") {
                            img = R.drawable.eleaf
                        } else {
                            if (model == "i3") {
                                img = R.drawable.i3
                            } else {
                                if (model == "Model 3") {
                                    img = R.drawable.model3
                                } else {
                                    img = R.drawable.models
                                }
                            }
                        }
                    }
                }
            }
            arrCar.add(Cars(this.intent.extras!!.get(i.toString()).toString(), title, img, description))
        }

        listView.adapter = CustomAdapter(applicationContext, arrCar)
        listView.setOnItemClickListener { _, _, position, _ ->

            if (this.intent.extras!!.getString("hasCar") == "nocarRented") {
                val nextIntent = Intent(this, ContractWriter::class.java)
                nextIntent.putExtra("user_id", this.intent.extras!!.get("user_id").toString())
                nextIntent.putExtra("VIN", (listView.adapter.getItem(position) as Cars).VIN)


                val ald: AlertDialog? = this.let {
                    val builder = AlertDialog.Builder(it)
                    builder.apply {
                        setPositiveButton("Rent"
                        ) { _, _ ->
                            startActivityForResult(nextIntent, LAUNCH_CONTRACT_WRITER)
                        }
                        setNegativeButton("Cancel"
                        ) { _, _ ->
                            // User cancelled the dialog

                        }
                    }
                    builder.create()
                }
                val manModYear=(listView.adapter.getItem(position) as Cars).name


                  val description=(listView.adapter.getItem(position) as Cars).description



                val dialogMessage = "You are about to rent: $manModYear \n $description."
                ald?.setMessage(dialogMessage)
                ald?.setTitle("Confirm selection")
                ald?.show()
            }
             else{
                 Toast.makeText(applicationContext,"Dispose the current car to get a new one!",Toast.LENGTH_LONG).show()

             }
        }



    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == LAUNCH_CONTRACT_WRITER) {
                if (resultCode == Activity.RESULT_OK) {
                    resultIntent.putExtra("code", data!!.getStringExtra("code").toString())
                    setResult(Activity.RESULT_OK, resultIntent)
                    this.finish()
                } else {
                    resultIntent.putExtra("code", data!!.getStringExtra("code").toString())
                    setResult(Activity.RESULT_CANCELED, resultIntent)

                }
            }
        }



}

