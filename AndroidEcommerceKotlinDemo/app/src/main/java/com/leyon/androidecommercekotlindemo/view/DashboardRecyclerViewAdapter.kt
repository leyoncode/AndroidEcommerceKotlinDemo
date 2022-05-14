package com.leyon.androidecommercekotlindemo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leyon.androidecommercekotlindemo.R
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Transactions
import com.leyon.androidecommercekotlindemo.viewmodel.DashboardViewModel

class DashboardRecyclerViewAdapter(val context: Context, val viewModel: DashboardViewModel) : RecyclerView.Adapter<DashboardRecyclerViewAdapter.TransactionViewHolder>()  {

    private var transactionsList : List<Transactions> = listOf() //set empty list that can be replaced

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.transaction_item,
            parent,
            false
        )

        return DashboardRecyclerViewAdapter.TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction : Transactions = transactionsList[position]

        holder.transactionId.text = transaction.transactionId.toString()
        // TODO: set product name.
        //holder.productName = transaction.
        holder.noOfItems.text = transaction.numProductItems.toString()
        holder.totalPrice.text = transaction.totalPrice.toString()
    }

    override fun getItemCount(): Int {
        return this.transactionsList.size
    }

    fun setList(transactionsList : List<Transactions>) {
        this.transactionsList = transactionsList
        notifyDataSetChanged()
    }

    class TransactionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val transactionId : TextView = itemView.findViewById<TextView>(R.id.transaction_id)
        val productName : TextView = itemView.findViewById<TextView>(R.id.transaction_product_name)
        val noOfItems : TextView = itemView.findViewById<TextView>(R.id.transaction_item_count)
        val totalPrice : TextView = itemView.findViewById<TextView>(R.id.transaction_total_price)

    }

}