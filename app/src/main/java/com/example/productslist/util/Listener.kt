package com.example.productslist.util

interface Listener {
    fun onFailure(message: String)
    fun onSuccess(item: Any?)
}