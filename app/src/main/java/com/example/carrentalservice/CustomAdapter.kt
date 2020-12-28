package com.example.carrentalservice

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

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
        var viewHolder:ViewHolder
        if(convertView==null)
        {
            var layout=LayoutInflater.from(context)
            view=layout.inflate(R.layout.cars_item_list,parent,false)
            viewHolder=ViewHolder(view)
            view.tag=viewHolder
        }
        else
        {
            view=convertView
            viewHolder=view.tag as ViewHolder
        }

        var car:Cars=getItem(position) as Cars
        viewHolder.txtNameCar.text=car.name
        viewHolder.ivImage.setImageResource(car.image)
        viewHolder.txtDescription.text=car.description

        return  view as View
    }

    override fun getItem(position: Int): Any {
        return car.get(position)
    }

    override fun getItemId(position: Int): Long {
            return position.toLong()
    }

    override fun getCount(): Int {
            return car.count()
    }
}