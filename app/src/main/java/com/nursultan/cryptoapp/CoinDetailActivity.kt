package com.nursultan.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.DataPointInterface
import com.jjoe64.graphview.series.LineGraphSeries
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
        viewModel.loadDailyInfoData("BTC")
        val series = LineGraphSeries<DataPoint>(arrayOf(DataPoint(1.0,5.0),DataPoint(2.0, 54.4),
            DataPoint(3.0, 54.4), DataPoint(4.0, 84.4),
            DataPoint(5.0, 84.4)
        ))

        graphCoinPrice.addSeries(series)
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
