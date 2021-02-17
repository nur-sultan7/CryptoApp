package com.nursultan.cryptoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.cryptoapp.R
import com.nursultan.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_price_info.view.*

class CoinInfoAdapter : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var coinPriceInfoList = listOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin_price_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coinPI = coinPriceInfoList[position]
        with(holder)
        {
            with(coinPI)
            {
                tvSymbols.text = fromsymbol + "/" + tosymbol
                tvPrice.text = price.toString()
                tvLastUpdate.text = getFormattedTime()
                Picasso.get()
                    .load(getFullImageURL())
                    .into(ivLogoCoin)
            }
        }

    }

    override fun getItemCount() = coinPriceInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSymbols = itemView.tvSymbols
        val tvPrice = itemView.tvPrice
        val tvLastUpdate = itemView.tvLastUpdate
        val ivLogoCoin = itemView.ivLogoCoin
    }

}