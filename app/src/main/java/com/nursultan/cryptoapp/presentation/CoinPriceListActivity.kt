package com.nursultan.cryptoapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.AdapterView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nursultan.cryptoapp.R
import com.nursultan.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.nursultan.cryptoapp.presentation.utils.MyRecyclerScroll
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }
    private val component by lazy {
        (application as CryptoApp).component
            .activitySubcomponent().create()
    }

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
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))


        adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.isNestedScrollingEnabled = false
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        binding.rvCoinPriceList.addOnScrollListener(object : MyRecyclerScroll() {
            override fun hide() {
               // binding.spinnerCoinPriceList.visibility = View.GONE
            }

            override fun show() {
             //   binding.spinnerCoinPriceList.visibility = View.VISIBLE
            }

        })


        liveListData = viewModel.getCoinInfoList(true)
        binding.spinnerCoinPriceList.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    liveListData.removeObservers(this@CoinPriceListActivity)
                    liveListData = viewModel.getCoinInfoList(position == 0)
                    liveListData.observe(this@CoinPriceListActivity) {
                        if (it != null && it.isNotEmpty())
                            adapter.submitList(it)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.spinnerCoinPriceList.setSelection(0)
        adapter.onCoinClickListener = {
            if (isOnePaneMode()) {
                launchActivityCoinDetail(it.fromSymbol)
            } else {
                launchFragmentCoinDetail(it.fromSymbol)
            }
        }
        adapter.onFavClickListener = { coinInfo: CoinInfo, isFav: Boolean ->
            when (isFav) {
                false -> viewModel.insertFavCoin(coinInfo)
                true -> viewModel.deleteFavCoin(coinInfo.fromSymbol)
            }

        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchActivityCoinDetail(fSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fSymbol
        )
        startActivity(intent)
    }

    private fun launchFragmentCoinDetail(fSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                CoinDetailFragment.newInstance(fSymbol)
            )
            .addToBackStack(null)
            .commit()
    }
}
