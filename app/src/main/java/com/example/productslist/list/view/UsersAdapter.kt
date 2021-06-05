package com.example.productslist.ui.list.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.productslist.R
import com.example.productslist.data.db.entities.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class UsersAdapter(private val products: ArrayList<Product>, private val context: Context) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvProductName.text = products[position].name
        Picasso
            .get()
            .load(products[position].imgUrl)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .fit()
            .centerCrop()
            .into(
                holder.imgUrl,
                //for loading cached img
                object: Callback {
                    override fun onSuccess() {}

                    override fun onError(e: java.lang.Exception?) {
                        // Try again online if cache failed
                        Picasso.get()
                            .load(products[position].imgUrl)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(holder.imgUrl)
                    }
                }
            )
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvProductName: TextView = itemView.findViewById(R.id.tv_name)
        var imgUrl: ImageView = itemView.findViewById(R.id.img_url)
    }

}