package com.example.carrentalservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.carrentalservice.R
import com.google.android.gms.common.util.Strings
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var  listView=findViewById(R.id.listView) as ListView
        var arrCar:ArrayList<Cars> =  ArrayList()
        arrCar.add(Cars("Skoda 2016",R.drawable.skoda,"3 litri ramasi"))
        arrCar.add(Cars("Toyota Auris 2017",R.drawable.toyota,"2 litri ramasi"))
        arrCar.add(Cars("Toyota Prius 2016",R.drawable.toyota_prius,"1 litru ramas"))

        listView.adapter=CustomAdapter(applicationContext,arrCar)

    }
}
