package com.leyon.androidecommercekotlindemo.model.repository

import android.content.Context
import com.leyon.androidecommercekotlindemo.model.roomdb.dao.TransactionsDAO
import com.leyon.androidecommercekotlindemo.model.roomdb.database.ProductsDatabase
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Products
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Transactions
import kotlinx.coroutines.flow.Flow

class TransactionsRepository(context: Context) {

    private val db : ProductsDatabase = ProductsDatabase.getDatabase(context)
    private val transactionsDAO : TransactionsDAO = db.getTransactionsDAO()

    val transactions : Flow<List<Transactions>> = transactionsDAO.getAllTransactions()

    suspend fun insertTransaction(transactions: Transactions) {
        transactionsDAO.insertTransaction(transactions)
    }

    suspend fun updateTransaction(transactions: Transactions) {
        transactionsDAO.updateTransaction(transactions)
    }

    suspend fun deleteTransaction(transactions: Transactions) {
        transactionsDAO.deleteTransaction(transactions)
    }

    suspend fun getTransactionById(id : Long) : Transactions {
        return transactionsDAO.getTransactionById(id)
    }

    suspend fun getProductById(id : Long) : Products {
        return transactionsDAO.getProductById(id)
    }
}