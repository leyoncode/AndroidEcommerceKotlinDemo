package com.leyon.androidecommercekotlindemo.model.roomdb.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @ColumnInfo(name = "productName")
    val productName: String,

    @ColumnInfo(name = "productPrice")
    val productPrice: Double,

    @ColumnInfo(name = "productStock")
    var productStock: Int
) {
    @PrimaryKey(autoGenerate = true)
    var productId: Long = 0
}