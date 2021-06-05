package com.example.productslist.data.repositories

import android.app.Application
import com.example.productslist.data.db.AppDatabase
import com.example.productslist.data.db.entities.Product
import com.example.productslist.data.network.ApiInterface
import com.example.productslist.data.network.SafeApiRequest
import com.example.productslist.data.network.responses.ProductsResponse

class ProductsRepository(application: Application, private val api: ApiInterface)
    : SafeApiRequest() {

    //db is an abstract class that cannot be injected, so it is initiated using singleton
    private val appDatabase = AppDatabase.getDatabase(application)

    //local db queries invoke ////////
    suspend fun getLocalProducts() :ArrayList<Product>{
        return appDatabase.getUserDao().getProducts() as ArrayList<Product>
    }

    suspend fun insertItemToLocal(products: List<Product>){
        appDatabase.getUserDao().addProducts(products = products)
    }

    //api requests calls //////
    suspend fun getProductsOnline() : ProductsResponse {
        return apiRequest { api.getProducts() }
    }

    //end api requests calls //////
}