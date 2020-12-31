package com.example.carrentalservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class PanaitCernaCars : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panait_cerna_cars)
        var  listView=findViewById(R.id.listView_tasnad) as ListView
        var arrCar:ArrayList<Cars> =  ArrayList()
        arrCar.add(Cars("Tesla X",R.drawable.tesla,"4 litri ramasi"))
        arrCar.add(Cars("Skoda 2016",R.drawable.skoda,"2 litri ramasi"))
        arrCar.add(Cars("Toyota Prius",R.drawable.toyota_prius,"6 litri ramasi"))

        listView.adapter=CustomAdapter(applicationContext,arrCar)
    }
}