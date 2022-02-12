package com.nursultan.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.nursultan.cryptoapp.R
import com.nursultan.cryptoapp.databinding.ActivityFavCoinListBinding
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.presentation.adapters.CoinFavAdapter
import javax.inject.Inject

class CoinFavActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFavCoinListBinding.inflate(layoutInflater)
    }
    private val component by lazy {
        (application as CryptoApp).component
            .activitySubcomponent().create()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinFavViewModel::class.java]
    }
    private val adapter by lazy {
        CoinFavAdapter(this)
    }
    private lateinit var liveFavCoin: LiveData<List<CoinInfo>>

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = getString(R.string.favourite_title)
        binding.rvFavCoins.adapter = adapter
        liveFavCoin = viewModel.getFavCoinList()
        liveFavCoin.observe(this) {
            adapter.submitList(it)
        }
        adapter.onFavClickListener = { coinInfo, isFav ->
            if (isFav) viewModel.deleteFavCoin(coinInfo.fromSymbol)
            else viewModel.addToFavCoin(coinInfo)
        }
        adapter.onItemClick = {
            launchCoinDetailActivity(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.let {
            val favCategory = menu.findItem(R.id.main_menu_fav_cat)
            val top30Category = menu.findItem(R.id.main_menu_top_30_cat)
            favCategory.setOnMenuItemClickListener {

                true
            }
            top30Category.setOnMenuItemClickListener {
                this.finish()
                true
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun launchCoinDetailActivity(fSymbol: String) {
        startActivity(CoinDetailActivity.newIntent(this, fSymbol))
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CoinFavActivity::class.java)
        }
    }
}
