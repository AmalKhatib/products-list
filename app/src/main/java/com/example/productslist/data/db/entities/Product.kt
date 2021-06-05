package com.example.productslist.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Database entity (table)*/
@Entity(tableName = "Product")
data class Product(
    @PrimaryKey val id: Int?,
    val name: String?,
    val popularity: String?,
    val brand: String?,
    val imgUrl: String?,
    val price: Double?
)