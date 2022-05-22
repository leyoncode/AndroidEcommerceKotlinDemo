package com.leyon.androidecommercekotlindemo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.leyon.androidecommercekotlindemo.model.repository.ProductRepository
import com.leyon.androidecommercekotlindemo.model.repository.TransactionsRepository
import com.leyon.androidecommercekotlindemo.model.storage.entity.Products
import com.leyon.androidecommercekotlindemo.model.storage.entity.Transactions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SearchProductViewModel(application: Application) : AndroidViewModel(application) {

    private val productRepo : ProductRepository = ProductRepository(application.applicationContext)
    private val transactionsRepo : TransactionsRepository = TransactionsRepository(application.applicationContext)

    // Functions for interacting with Products entity data in database

    fun getProductLiveData() : LiveData<List<Products>> {
        return productRepo.products.asLiveData()
    }

    // IMPORTANT! in the searchString add a wildcard '%' before and after the string for best results
    fun searchForProductWithString(searchText : String) : LiveData<List<Products>> {
        return productRepo.searchForProductWithString(searchText).asLiveData()
    }

    fun updateProduct(products: Products) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepo.updateProduct(products)
        }
    }

    fun getProductById(id : Long) : Products {
        var x : Products ?= null
        runBlocking {
            x = productRepo.getProductById(id)
        }

        if (x == null) {
            x = Products("Product not found in stock", 0.0,0)
        }

        return x!!
    }

    fun insertSaleToTransactions(transactions: Transactions) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionsRepo.insertTransaction(transactions)
        }
    }
}