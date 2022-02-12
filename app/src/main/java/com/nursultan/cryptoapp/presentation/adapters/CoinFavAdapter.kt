package com.nursultan.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nursultan.cryptoapp.R
import com.nursultan.cryptoapp.databinding.ItemCoinPriceInfoBinding
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.presentation.utils.CoinInfoDiffUtilCallback
import com.squareup.picasso.Picasso

class CoinFavAdapter(private val context: Context) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffUtilCallback) {
    lateinit var onFavClickListener: (CoinInfo, Boolean) -> Unit
    lateinit var onItemClick: (String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinPriceInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val favCoinInfo = getItem(position)
        with(holder.binding)
        {
            with(favCoinInfo)
            {
                btnFav.isLiked = isFav
                tvLastUpdateLabel.text = String.format(
                    context.getString(R.string.last_update_template), lastUpdate
                )
                tvSymbols10.text = String.format("%s/%s", fromSymbol, toSymbol)
                tvPrice10.text = (price).toString().take(8)
                Picasso.get()
                    .load(imageUrl)
                    .into(ivLogoCoin10)
                btnFav.setOnClickListener {
                    onFavClickListener.invoke(favCoinInfo, btnFav.isLiked)
                }
            }
        }
        holder.itemView.setOnClickListener {
            onItemClick.invoke(favCoinInfo.fromSymbol)
        }
    }

}