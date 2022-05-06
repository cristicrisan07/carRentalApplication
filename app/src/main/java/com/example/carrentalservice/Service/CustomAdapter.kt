package com.example.carrentalservice.Service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.carrentalservice.Model.Cars
import com.example.carrentalservice.R

class CustomAdapter(var context:Context,var car:ArrayList<Cars>):BaseAdapter() {
    private class ViewHolder(row:View?)
    {
        var txtNameCar:TextView
        var ivImage:ImageView
        var txtDescription:TextView

        init {
            this.txtNameCar=row?.findViewById(R.id.nameOfCar) as TextView
            this.ivImage=row?.findViewById(R.id.online_image) as ImageView
            this.txtDescription=row?.findViewById(R.id.description_text) as TextView

        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View
        var viewHolder: ViewHolder
        if(convertView==null)
        {
            var layout=LayoutInflater.from(context)
            view=layout.inflate(R.layout.cars_item_list,parent,false)
            viewHolder= ViewHolder(view)
            view.tag=viewHolder
        }
        else
        {
            view=convertView
            viewHolder=view.tag as ViewHolder
        }

        var car: Cars =getItem(position) as Cars
        viewHolder.txtNameCar.text=car.name
        viewHolder.ivImage.setImageResource(car.image)
        viewHolder.txtDescription.text=car.description

        return view
    }

    override fun getItem(position: Int): Any {
        return car[position]
    }

    override fun getItemId(position: Int): Long {
            return position.toLong()
    }

    override fun getCount(): Int {
            return car.count()
    }
}