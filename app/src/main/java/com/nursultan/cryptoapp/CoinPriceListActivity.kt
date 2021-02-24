package com.nursultan.cryptoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil

import com.nursultan.cryptoapp.adapters.CoinInfoAdapter
import com.nursultan.cryptoapp.adapters.CoinInfoAdapter.OnCoinClickListener
import com.nursultan.cryptoapp.pojo.CoinPriceInfo
import com.nursultan.cryptoapp.utils.CoinDiffUtilCallback
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
           val coinDiffUtilCallback = CoinDiffUtilCallback(adapter.coinPriceInfoList, it)
            val diffResult = DiffUtil.calculateDiff(coinDiffUtilCallback)
            adapter.coinPriceInfoList = it
            diffResult.dispatchUpdatesTo(adapter)
        })
        adapter.setOnCoinClickListener(object : OnCoinClickListener {
            override fun onClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromsymbol
                )
                startActivity(intent)
            }
        })

    }

}
