package com.example.carrentalservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class TasnadCarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasnad_cars)
        var  listView=findViewById(R.id.listView_tasnad) as ListView
        var arrCar:ArrayList<Cars> =  ArrayList()
        arrCar.add(Cars("Tesla X",R.drawable.tesla,"4 litri ramasi"))

        listView.adapter=CustomAdapter(applicationContext,arrCar)
    }
}