package com.leyon.androidecommercekotlindemo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Products
import com.leyon.androidecommercekotlindemo.viewmodel.HomeViewModel

class HomeRecyclerViewAdapter(val context: Context, val viewModel: HomeViewModel) : RecyclerView.Adapter<HomeRecyclerViewAdapter.ProductViewHolder>() {

    private var productsList : List<Products> = listOf() //set empty list that can be replaced

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.product_item,
                parent,
                false
            )

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productItem = productsList[position]

        holder.productName.text = productItem.productName
        holder.productPrice.text = productItem.productPrice.toString()
        //holder.productImage.setImageBitmap()

        holder.buyButton.setOnClickListener{
            buyProduct(productItem.productId)
        }
    }

    override fun getItemCount(): Int {
        //Log.e("Count", ("item count = " + (productList.size).toString()))
        return this.productsList.size
    }

    fun setList(productsList : List<Products>) {
        this.productsList = productsList
        notifyDataSetChanged()
    }

    fun buyProduct(productID : Long) {

        MaterialAlertDialogBuilder(context)
            .setTitle("Verify Purchase")
            .setMessage("Do you want to buy this")
            //.setNeutralButton("") { dialog, which ->
                // Respond to neutral button press
            //}
            .setNegativeButton("Reject") { dialog, which ->
                // do nothing when press reject
            }
            .setPositiveButton("Confirm") { dialog, which ->
                // buy product when select confirm
            }
            .show()


    }

    //view holder class
    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val productImage : ImageView = itemView.findViewById<ImageView>(R.id.productImage)
        val productName : TextView = itemView.findViewById<TextView>(R.id.productlabel)
        val productPrice : TextView = itemView.findViewById<TextView>(R.id.productPrice)

        val buyButton : Button = itemView.findViewById<Button>(R.id.buyButton)
    }
}