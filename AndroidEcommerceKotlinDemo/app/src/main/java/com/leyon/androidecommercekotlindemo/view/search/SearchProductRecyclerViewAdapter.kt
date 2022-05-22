package com.leyon.androidecommercekotlindemo.view.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.model.storage.entity.Products
import com.leyon.androidecommercekotlindemo.model.storage.entity.Transactions
import com.leyon.androidecommercekotlindemo.view.sendNotification
import com.leyon.androidecommercekotlindemo.viewmodel.SearchProductViewModel

class SearchProductRecyclerViewAdapter(val context: Context, val viewModel: SearchProductViewModel) : RecyclerView.Adapter<SearchProductRecyclerViewAdapter.SearchItemViewHolder>() {

    private var productsList : List<Products> = listOf() //set empty list that can be replaced

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.product_item, //reusing this, which was originally made for recycler view in home fragment
            parent,
            false
        )

        return SearchItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val productItem = productsList[position]

        holder.productName.text = productItem.productName
        holder.productPrice.text = productItem.productPrice.toString()
        holder.productStock.text = productItem.productStock.toString()
        //holder.productImage.setImageBitmap()

        holder.buyButton.setOnClickListener{
            buyProduct(position)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    fun setList(productList: MutableList<Products>) {
        this.productsList = productList
        notifyDataSetChanged()
    }

    //copied from HomeRecyclerViewAdapter
    private fun buyProduct(position: Int) {
        //use position as index to get product from productsList and update it using viewModel

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
                val tmpProductRef = productsList[position] //get a temporary reference to the product

                //reducing only one stock for now. later add form to manually select no of items
                val numOfItemsToBuy = 1

                //verify noOfItemsToBuy
                if (numOfItemsToBuy > tmpProductRef.productStock && numOfItemsToBuy <= 0) {
                    Toast.makeText(context,"Invalid number of items", Toast.LENGTH_SHORT).show()
                } else {
                    val updateProduct : Products = Products(tmpProductRef.productName, tmpProductRef.productPrice, tmpProductRef.productStock - numOfItemsToBuy)
                    updateProduct.productId = tmpProductRef.productId

                    viewModel.updateProduct(updateProduct) //send updated product to database

                    //send notification about successful buy
                    sendNotification(context,
                        "Successfully bought ${tmpProductRef.productName}",
                        "Bought product ${tmpProductRef.productName}.\nPrice:${tmpProductRef.productPrice * numOfItemsToBuy}"
                    )

                    //add sale transaction
                    val newTransaction : Transactions = Transactions(tmpProductRef.productId, numOfItemsToBuy , tmpProductRef.productPrice * 1)
                    viewModel.insertSaleToTransactions(newTransaction)
                }
            }
            .show()
    }

    class SearchItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val productImage : ImageView = itemView.findViewById<ImageView>(R.id.productImage)
        val productName : TextView = itemView.findViewById<TextView>(R.id.productlabel)
        val productPrice : TextView = itemView.findViewById<TextView>(R.id.productPrice)
        val productStock : TextView = itemView.findViewById<TextView>(R.id.productStock)

        val buyButton : Button = itemView.findViewById<Button>(R.id.buyButton)
    }
}