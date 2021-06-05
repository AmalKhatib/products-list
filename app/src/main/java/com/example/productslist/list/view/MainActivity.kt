package com.example.productslist.ui.list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productslist.R
import com.example.productslist.data.db.entities.Product
import com.example.productslist.ui.list.viewmodel.MainActivityViewModel
import com.example.productslist.ui.list.viewmodel.MainActivityViewModelFactory
import com.example.productslist.util.Listener
import com.example.productslist.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import kotlin.collections.ArrayList

/*
* to make our dependencies accessible
* we need to make our class Kodein aware
* by implementing the KodeinAware interface */
class MainActivity : AppCompatActivity(), KodeinAware, Listener {

    //override the kodein variable and get our dependency by instance
    override val kodein by kodein()
    private val mFactory: MainActivityViewModelFactory by instance()

    private val mViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    // region initViews
    private fun initViews(){
        mViewModel?.listener = this
        mViewModel?.getProducts()!!
    }
    //end region initViews

    private fun fillAdapter(products: ArrayList<Product>){
        rv_products.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val usersAdapter = UsersAdapter(products, this)

        rv_products.adapter = usersAdapter
    }

    override fun onFailure(message: String) {
        toast(message)
    }

    override fun onSuccess(item: Any?) {
        item as ArrayList<Product>
        fillAdapter(item)
    }

}