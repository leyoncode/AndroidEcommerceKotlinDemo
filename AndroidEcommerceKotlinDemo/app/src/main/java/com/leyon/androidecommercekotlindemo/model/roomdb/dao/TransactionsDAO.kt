package com.leyon.androidecommercekotlindemo.model.roomdb.dao

import androidx.room.*
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Products
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Transactions
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transaction: Transactions)

    @Update
    suspend fun updateTransaction(transaction: Transactions)

    @Delete
    suspend fun deleteTransaction(transaction: Transactions)

    @Query("SELECT * FROM transactions ORDER BY transactionId ASC")
    fun getAllTransactions() : Flow<List<Transactions>>

    @Query("SELECT * FROM transactions WHERE transactionId = :id LIMIT 1")
    suspend fun getTransactionById(id : Long) : Transactions

    @Query("SELECT * FROM products WHERE productId = :id")
    suspend fun getProductById(id : Long) : Products
}