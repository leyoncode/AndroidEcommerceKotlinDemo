package com.leyon.androidecommercekotlindemo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.leyon.androidecommercekotlindemo.model.repository.ProductRepository
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Product
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val productRepo : ProductRepository = ProductRepository(application.applicationContext)

    fun getProductLiveData() : LiveData<List<Product>> {

        return productRepo.products.asLiveData()
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            productRepo.insertProduct(product)
        }
    }


    fun updateProduct(product: Product) {
        viewModelScope.launch {
            productRepo.updateProduct(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            productRepo.deleteProduct(product)
        }
    }

    fun getProductById(id : Long) : Product {
        var x : Product ?= null
        runBlocking {
            x = productRepo.getProductById(id)
        }
        return x!!
    }
}