package com.nursultan.cryptoapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import com.nursultan.cryptoapp.R
import com.nursultan.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.nursultan.cryptoapp.presentation.adapters.CoinInfoAdapter.OnCoinClickListener
import com.nursultan.cryptoapp.utils.CoinInfoDiffUtilCallback

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: CoinViewModel
    private lateinit var adapter: CoinInfoAdapter
    private lateinit var liveListData: LiveData<List<CoinInfo>>

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.let {
            val favCategory = menu.findItem(R.id.main_menu_fav_cat)
            val top30Category = menu.findItem(R.id.main_menu_top_30_cat)
            favCategory.setOnMenuItemClickListener {
                startActivity(CoinFavActivity.newIntent(this@CoinPriceListActivity))
                true
            }
            top30Category.setOnMenuItemClickListener {
                true
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        liveListData = viewModel.getCoinInfoList(true)
        binding.spinnerCoinPriceList.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    liveListData.removeObservers(this@CoinPriceListActivity)
                    liveListData = viewModel.getCoinInfoList(position == 0)
                    liveListData.observe(this@CoinPriceListActivity) {
                        adapter.submitList(it)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.spinnerCoinPriceList.setSelection(0)
        adapter.setOnCoinClickListener(object : OnCoinClickListener {
            override fun onClick(coinPriceInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }

        })
        adapter.onFavClickListener = object : CoinInfoAdapter.OnFavClickListener {
            override fun onClick(coinPriceInfo: CoinInfo, isFav: Boolean) {

                with(viewModel)
                {
//                    when (isFav) {
//                        true ->
//                            deleteFavCoin(FavCoinInfoDbModel(coinPriceInfo))
//                        false ->
//                            insertFavCoin(FavCoinInfoDbModel(coinPriceInfo))
//                    }
                }
            }
        }
    }
}
