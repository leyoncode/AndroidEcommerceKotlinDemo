package com.leyon.androidecommercekotlindemo.model.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM products ORDER BY productId ASC")
    fun getAllProducts() : Flow<List<Product>>

    @Query("SELECT * FROM products WHERE productId = :id LIMIT 1")
    suspend fun getProductById(id : Long) : Product
}