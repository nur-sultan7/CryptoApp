package com.nursultan.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nursultan.cryptoapp.adapters.CoinInfoAdapter
import com.nursultan.cryptoapp.adapters.CoinInfoAdapter.OnCoinClickListener
import com.nursultan.cryptoapp.pojo.CoinPriceInfo

import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel;
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter=CoinInfoAdapter(this)
        rvCoinPriceList.adapter=adapter
        viewModel= ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinPriceInfoList=it
        })
        adapter.setOnCoinClickListener(object : OnCoinClickListener {
            override fun onClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("TEST_ON_COIN_CLICK",coinPriceInfo.fromsymbol)
            }
        })

    }

}
