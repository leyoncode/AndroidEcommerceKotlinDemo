package com.leyon.androidecommercekotlindemo.model.roomdb.dao

import androidx.room.*
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Products)

    @Update
    suspend fun updateProduct(product: Products)

    @Delete
    suspend fun deleteProduct(product: Products)

    @Query("SELECT * FROM products ORDER BY productId ASC")
    fun getAllProducts() : Flow<List<Products>>

    @Query("SELECT * FROM products WHERE productId = :id LIMIT 1")
    suspend fun getProductById(id : Long) : Products
}