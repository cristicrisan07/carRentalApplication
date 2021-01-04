package com.example.carrentalservice

import android.app.Activity
import android.content.Intent
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
    val LAUNCH_CONTRACT_WRITER=1
    var resultIntent=Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var listView = findViewById(R.id.listView) as ListView
        var arrCar: ArrayList<Cars> = ArrayList()

        for (i in 0 until (this.intent.extras?.size()!!-1)) {
            arrCar.add(Cars(this.intent.extras!!.get(i.toString()) as String, R.drawable.skoda, "ceva ceva"))
        }

        listView.adapter = CustomAdapter(applicationContext, arrCar)
        listView.setOnItemClickListener { _, _, position, _ ->

           val nextIntent = Intent(this, ContractWriter::class.java)
            nextIntent.putExtra("user_id", this.intent.extras!!.get("user_id").toString())
            nextIntent.putExtra("VIN", (listView.adapter.getItem(position) as Cars).name)

            startActivityForResult(nextIntent,LAUNCH_CONTRACT_WRITER)


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    if(requestCode==LAUNCH_CONTRACT_WRITER)
    {
        if(resultCode== Activity.RESULT_OK)
        {
            resultIntent.putExtra("code",data!!.getStringExtra("code").toString())
            setResult(Activity.RESULT_OK,resultIntent)
            this.finish()
        }
        else {
            resultIntent.putExtra("code",data!!.getStringExtra("code").toString())
            setResult(Activity.RESULT_CANCELED,resultIntent)
            this.finish()
        }
    }
    }
}

