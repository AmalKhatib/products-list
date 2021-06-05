package com.example.productslist.data.network

import com.example.productslist.data.network.responses.ProductsResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    suspend fun getProducts() : Response<ProductsResponse>

    companion object{
        operator fun invoke()
                    : ApiInterface {

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val okkHttpclient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://unrwatestapi.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiInterface::class.java)
        }

    }

}