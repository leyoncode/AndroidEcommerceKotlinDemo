package com.leyon.androidecommercekotlindemo.model.repository

import android.content.Context
import com.leyon.androidecommercekotlindemo.model.storage.dao.ProductDAO
import com.leyon.androidecommercekotlindemo.model.storage.database.ProductsDatabase
import com.leyon.androidecommercekotlindemo.model.storage.entity.Products
import kotlinx.coroutines.flow.Flow

class ProductRepository(context: Context) {

    private val db : ProductsDatabase = ProductsDatabase.getDatabase(context)
    private val productDAO : ProductDAO = db.getProductsDAO()

    val products : Flow<List<Products>> = productDAO.getAllProducts()

    suspend fun insertProduct(products: Products) {
        productDAO.insertProduct(products)
    }

    suspend fun updateProduct(products: Products) {
        productDAO.updateProduct(products)
    }

    suspend fun deleteProduct(products: Products) {
        productDAO.deleteProduct(products)
    }

    suspend fun getProductById(id : Long) : Products {
        return productDAO.getProductById(id)
    }
}