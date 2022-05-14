package com.leyon.androidecommercekotlindemo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.leyon.androidecommercekotlindemo.model.repository.ProductRepository
import com.leyon.androidecommercekotlindemo.model.repository.TransactionsRepository
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Products
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Transactions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val productRepo : ProductRepository = ProductRepository(application.applicationContext)

    // Functions for interacting with Products entity data in database

    fun getProductLiveData() : LiveData<List<Products>> {

        return productRepo.products.asLiveData()
    }

    fun insertProduct(products: Products) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepo.insertProduct(products)
        }
    }


    fun updateProduct(products: Products) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepo.updateProduct(products)
        }
    }

    fun deleteProduct(products: Products) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepo.deleteProduct(products)
        }
    }

    fun getProductById(id : Long) : Products {
        var x : Products ?= null
        runBlocking {
            x = productRepo.getProductById(id)
        }
        return x!!
    }
}