package com.leyon.androidecommercekotlindemo.model.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Products(
    @ColumnInfo(name = "productName")
    var productName: String,

    @ColumnInfo(name = "productPrice")
    var productPrice: Double,

    @ColumnInfo(name = "productStock")
    var productStock: Int
) {
    @PrimaryKey(autoGenerate = true)
    var productId: Long = 0
}