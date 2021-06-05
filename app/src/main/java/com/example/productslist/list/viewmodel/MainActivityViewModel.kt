package com.example.productslist.ui.list.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.productslist.data.repositories.ProductsRepository
import com.example.productslist.data.utils.ApiException
import com.example.productslist.data.utils.ConnectivityStatus
import com.example.productslist.data.utils.Coroutines
import com.example.productslist.util.Listener
import java.lang.Exception

class MainActivityViewModel(
    private val productsRepository: ProductsRepository,
    private val application: Application
) : ViewModel() {

    var listener: Listener? = null

    fun getProducts() {
        Coroutines.main {
            val offlineProducts = productsRepository.getLocalProducts()

            // if there is  internet connection, check the online data
            if (ConnectivityStatus.isOnline(application)) {
                try {
                    val onlineProducts = productsRepository.getProductsOnline()
                    onlineProducts?.let {
                        productsRepository.insertItemToLocal(onlineProducts.items)
                    }
                } catch (e: ApiException) {
                    listener?.onFailure(e.message!!)
                } catch (e: Exception) {
                    listener?.onFailure(e.message!!)
                }
                listener?.onSuccess(productsRepository.getLocalProducts())
                return@main
            }
            listener?.onSuccess(offlineProducts)
            return@main
        }

    }

}