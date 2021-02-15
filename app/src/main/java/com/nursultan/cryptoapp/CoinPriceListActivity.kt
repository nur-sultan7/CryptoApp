package com.nursultan.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import io.reactivex.rxjava3.disposables.CompositeDisposable

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel;
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        viewModel= ViewModelProviders.of(this)[CoinViewModel::class.java]

//        viewModel.priceList.observe(this, Observer {
//            Log.d("TEST_DATA","Success in Activity $it")
//        })
    viewModel.getCoinPriceInfo("BTC").observe(this, Observer {
        Log.d("TEST_DATA","Success in Activity load coin info $it")
    })
    }

}
