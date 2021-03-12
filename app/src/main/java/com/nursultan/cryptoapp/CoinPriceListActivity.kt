package com.nursultan.cryptoapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import com.nursultan.cryptoapp.adapters.CoinInfoAdapter
import com.nursultan.cryptoapp.adapters.CoinInfoAdapter.OnCoinClickListener
import com.nursultan.cryptoapp.pojo.CoinPriceInfo
import com.nursultan.cryptoapp.utils.CoinDiffUtilCallback
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter: CoinInfoAdapter
    private lateinit var liveListData: LiveData<List<CoinPriceInfo>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        adapter = CoinInfoAdapter(this)
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProviders.of(this)[CoinViewModel::class.java]
        liveListData = viewModel.priceListDesc
        spinnerCoinPriceList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                liveListData.removeObservers(this@CoinPriceListActivity)
                liveListData = if (position == 0)
                    viewModel.priceListDesc
                else
                    viewModel.priceListAsc
                liveListData.observe(this@CoinPriceListActivity, Observer { updateList(it) })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        spinnerCoinPriceList.setSelection(0)
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

    private fun updateList(coinPriceList: List<CoinPriceInfo>) {
        val coinDiffUtilCallback = CoinDiffUtilCallback(adapter.coinPriceInfoList, coinPriceList)
        val diffResult = DiffUtil.calculateDiff(coinDiffUtilCallback)
        adapter.coinPriceInfoList = coinPriceList
        diffResult.dispatchUpdatesTo(adapter)
    }

}
