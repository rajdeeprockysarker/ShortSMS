package com.raj.shortsms

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import android.view.LayoutInflater
import kotlinx.android.synthetic.main.custom_view.view.*

class SmsAdapter(val users: ArrayList<Conversation>) : RecyclerView.Adapter<SmsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the custom view from xml layout file
        val v: View = LayoutInflater.from(parent?.context)
            .inflate(R.layout.custom_view,parent,false)

        // Return the view holder
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Display the current user full name and location in view holder custom view
        holder?.name?.text = users.get(position).number
        holder?.location?.text = users.get(position).number
    }


    override fun getItemCount(): Int {

        return users.size
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.tv_name
        val location = itemView.tv_location
    }


    // This two methods useful for avoiding duplicate item
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}