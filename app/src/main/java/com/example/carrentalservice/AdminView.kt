package com.example.carrentalservice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import kotlin.random.Random

class AdminView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_view)
        var viewMode=findViewById<Switch>(R.id.viewmode)
        var uppertextView=findViewById<TextView>(R.id.SubscribedAccountsTextView)
        viewMode.isChecked = false


            var listView = findViewById<ListView>(R.id.cars_list)
        listView.visibility = VISIBLE
        uppertextView.visibility= INVISIBLE
        uppertextView.text= "There  are "+Random.nextInt(4).toString()+" people currently subscribed."
            var arrCar: ArrayList<Cars> = ArrayList()

            val nrOfCars = this.intent.extras!!.get("nrOfCars") as Int
            for (i in 0 until nrOfCars) {

                val title =
                    (this.intent.extras!!.get("manufacturer$i") as String + " " + this.intent.extras!!.get(
                        "model$i"
                    ) as String + " " + this.intent.extras!!.get("Year$i") as String)
                var description: String?
                description =
                    "Battery level: " + this.intent.extras!!.get("Battery$i") as String + "%\nPrice per km:" + this.intent.extras!!.get(
                        "Price$i"
                    ) as String + "€ "

                var img: Int?
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
                            img = if (model == "eLeaf") {
                                R.drawable.eleaf
                            } else {
                                if (model == "i3") {
                                    R.drawable.i3
                                } else {
                                    if (model == "Model 3") {
                                        R.drawable.model3
                                    } else {
                                        R.drawable.models
                                    }
                                }
                            }
                        }
                    }
                }
                arrCar.add(
                    Cars(
                        this.intent.extras!!.get(i.toString()).toString(),
                        title,
                        img,
                        description
                    )
                )
            }
        viewMode.setOnClickListener {
            if (viewMode.isChecked) {
                listView.visibility = INVISIBLE
                uppertextView.visibility= VISIBLE
                uppertextView.text= "There  are "+Random.nextInt(4).toString()+" people currently subscribed."
            } else {


                uppertextView.visibility = TextView.INVISIBLE
                listView.adapter = CustomAdapter(applicationContext, arrCar)
                listView.visibility=VISIBLE
            }
        }
    }

}