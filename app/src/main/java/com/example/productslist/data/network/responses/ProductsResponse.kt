package com.example.productslist.data.network.responses

import com.example.productslist.data.db.entities.Product

data class ProductsResponse(
    val items: List<Product>,
    val message: String,
    val status: Int
)