package com.leyon.androidecommercekotlindemo.model.roomdb.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leyon.androidecommercekotlindemo.model.roomdb.dao.ProductDAO
import com.leyon.androidecommercekotlindemo.model.roomdb.dao.TransactionsDAO
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Products
import com.leyon.androidecommercekotlindemo.model.roomdb.entity.Transactions

@Database(
    entities = [Products::class, Transactions::class],
    version = 1
)
abstract class ProductsDatabase: RoomDatabase() {

    //call like> ProductsDatabase(activity!!).getProductsDAO()
    abstract fun getProductsDAO() : ProductDAO
    abstract fun getTransactionsDAO() : TransactionsDAO

    companion object {

        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        fun getDatabase(context: Context): ProductsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "products_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}