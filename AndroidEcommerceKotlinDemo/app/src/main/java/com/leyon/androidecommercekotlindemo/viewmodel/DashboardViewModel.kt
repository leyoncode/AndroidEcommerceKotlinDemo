package com.leyon.androidecommercekotlindemo.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.leyon.androidecommercekotlindemo.model.repository.TransactionsRepository
import com.leyon.androidecommercekotlindemo.model.storage.entity.Products
import com.leyon.androidecommercekotlindemo.model.storage.entity.Transactions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val transactionRepo : TransactionsRepository = TransactionsRepository(application.applicationContext)

    // Functions for interacting with Transactions entity data in database

    fun getTransactionsLiveData() : LiveData<List<Transactions>> {
        return transactionRepo.transactions.asLiveData()
    }

    fun insertTransaction(transactions: Transactions) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepo.insertTransaction(transactions)
        }
    }


    fun updateTransaction(transactions: Transactions) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepo.updateTransaction(transactions)
        }
    }

    fun deleteTransaction(transactions: Transactions) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepo.deleteTransaction(transactions)
        }
    }

    fun getTransactionById(id : Long) : Transactions {
        var x : Transactions?= null
        runBlocking {
            x = transactionRepo.getTransactionById(id)
        }
        return x!!
    }

    fun getProductById(id : Long) : Products {
        var x : Products?= null

        runBlocking {
            x = transactionRepo.getProductById(id)
        }

        if (x == null) {
            x = Products("Product not found in stock", 0.0,0)
        }

        return x!!
    }
}