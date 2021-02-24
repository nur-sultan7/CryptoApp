package com.nursultan.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL))
        {
            finish()
            return
        }
        val fromSymbol= intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel= ViewModelProviders.of(this)[CoinViewModel::class.java]
        viewModel.getCoinPriceInfo(fromSymbol?:"").observe(this, Observer {
            Log.d("DETAIL_INFO", it.toString())
        })
    }

    companion object
    {
        const val EXTRA_FROM_SYMBOL ="fSym"
    }
}
