package com.nursultan.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

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
            tvFSym.text=it.fromsymbol
            tvTSym.text=it.tosymbol
            tvPrice.text=it.price.toString()
            tvMinDay.text=it.lowday.toString()
            tvMaxDay.text= it.highday.toString()
            tvLastMarket.text=it.lastmarket
            tvLastUpdate.text=it.getFormattedTime()
            Picasso.get()
                .load(it.getFullImageURL())
                .into(ivLogoCoin)
        })
    }

    companion object
    {
        const val EXTRA_FROM_SYMBOL ="fSym"

        fun newIntent(context: Context, fromSymbol: String):Intent
        {
            val intent =Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL,fromSymbol)
            return intent
        }
    }
}
