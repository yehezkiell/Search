package com.example.yehezkiel.cermati.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yehezkiel.cermati.Model.Item
import com.example.yehezkiel.cermati.Model.UsersX
import com.example.yehezkiel.cermati.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_layout.view.*

/**
 * Created by Yehezkiel on 1/26/2019.
 */
class SearchAdapter(val context: Context, val item: UsersX) : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get() // give it the context
                .load(item.items?.get(position)?.avatar_url) // load the image
                .resize(60,60)
                .into(holder?.photo_view)
        holder?.name_view?.text = item.items?.get(position)?.login.toString()
    }

    override fun getItemCount(): Int {
        return item.items!!.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SearchAdapter.ViewHolder{
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_layout, parent, false))
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val photo_view = itemView.photo_view
        val name_view = itemView.name_view
    }


}