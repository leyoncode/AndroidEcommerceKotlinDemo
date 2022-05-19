package com.leyon.androidecommercekotlindemo.model.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transactions(
    @ColumnInfo(name = "productIdBought")
    val productIdBought : Long, //id of product bought. not setting foreign key to avoid losing record when product no longer in db
    @ColumnInfo(name = "numProductItems")
    val numProductItems : Int, //number of items of the product bought
    @ColumnInfo(name = "totalPrice")
    val totalPrice : Double //how much spent on these
) {
    @PrimaryKey(autoGenerate = true)
    var transactionId: Long = 0
}