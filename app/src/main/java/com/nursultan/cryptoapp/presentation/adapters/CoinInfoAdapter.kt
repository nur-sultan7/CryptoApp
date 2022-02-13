package com.nursultan.cryptoapp.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.cryptoapp.R
import com.nursultan.cryptoapp.databinding.ItemCoinPriceInfoBinding
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.presentation.utils.CoinInfoDiffUtilCallback
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffUtilCallback) {

    lateinit var onCoinClickListener: (CoinInfo) -> Unit
    var onFavClickListener: ((CoinInfo, Boolean) -> Unit)? = null
    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        Log.d("onCreateViewHolder", "created: ${++count}")
        val binding = ItemCoinPriceInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinPI = getItem(position)
        with(holder.binding)
        {
            with(coinPI)
            {
                tvSymbols10.text = String.format(
                    context.resources.getString(R.string.symbols_template),
                    fromSymbol,
                    toSymbol
                )
                tvPrice10.text = price.toString().take(8)
                tvLastUpdateLabel.text = String.format(
                    context.resources.getString(R.string.last_update_template),
                    lastUpdate
                )
                btnFav.isLiked = isFav
                Picasso.get()
                    .load(imageUrl)
                    .into(ivLogoCoin10)
            }
            root.setOnClickListener {
                onCoinClickListener.invoke(coinPI)
            }
            btnFav.setOnClickListener {
                onFavClickListener?.invoke(
                    coinPI,
                    btnFav.isLiked
                )
                btnFav.isLiked = !btnFav.isLiked
            }
        }

    }
}