package com.leyon.androidecommercekotlindemo.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Product

class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewAdapter.ProductViewHolder>() {

    private var productList : List<Product> = listOf() //set empty list that can be replaced

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.product_item,
                parent,
                false
            )

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productItem = productList[position]

        holder.productName.text = productItem.productName
        holder.productPrice.text = productItem.productPrice.toString()
        //holder.productImage.setImageBitmap()
    }

    override fun getItemCount(): Int {
        //Log.e("Count", ("item count = " + (productList.size).toString()))
        return this.productList.size
    }

    fun setList(productList : List<Product>) {
        this.productList = productList
        notifyDataSetChanged()
    }

    //view holder class
    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val productImage : ImageView = itemView.findViewById<ImageView>(R.id.productImage)
        val productName : TextView = itemView.findViewById<TextView>(R.id.productlabel)
        val productPrice : TextView = itemView.findViewById<TextView>(R.id.productPrice)

    }
}