package com.example.productslist.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productslist.data.db.entities.Product

/*
* Database operations*/
@Dao
interface UserDao {

    @Query("SELECT * FROM PRODUCT")
    suspend fun getProducts() : List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun addProducts(products: List<Product>)


}