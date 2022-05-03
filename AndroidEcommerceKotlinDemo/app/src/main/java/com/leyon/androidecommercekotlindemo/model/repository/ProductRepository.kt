package com.leyon.androidecommercekotlindemo.model.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.leyon.androidecommercekotlindemo.model.roomdb.dao.ProductDAO
import com.leyon.androidecommercekotlindemo.model.roomdb.database.ProductsDatabase
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(context: Context) {

    private val db : ProductsDatabase = ProductsDatabase.getDatabase(context)
    private val productDAO : ProductDAO = db.getProductsDAO()

    val products : Flow<List<Product>> = productDAO.getAllProducts()

    suspend fun insertProduct(product: Product) {
        productDAO.insertProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productDAO.updateProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDAO.deleteProduct(product)
    }

    suspend fun getProductById(id : Long) : Product {
        return productDAO.getProductById(id)
    }
}