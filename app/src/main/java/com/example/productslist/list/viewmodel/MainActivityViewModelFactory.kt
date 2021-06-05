package com.example.productslist.ui.list.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productslist.data.repositories.ProductsRepository

class MainActivityViewModelFactory(private val application: Application, private val productsRepository: ProductsRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return MainActivityViewModel(productsRepository, application) as T
    }
}