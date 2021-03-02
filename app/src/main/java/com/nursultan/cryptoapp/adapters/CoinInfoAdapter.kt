package com.nursultan.cryptoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nursultan.cryptoapp.R
import com.nursultan.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_price_info.view.*

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var coinPriceInfoList = listOf<CoinPriceInfo>()
//        set(value) {
//            field = value
//        }
    private  var  onCoinClickListener: OnCoinClickListener? =null

    interface OnCoinClickListener{
        fun onClick(coinPriceInfo: CoinPriceInfo)
    }
    fun setOnCoinClickListener(onClickListener: OnCoinClickListener)
    {
        this.onCoinClickListener=onClickListener
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
                tvSymbols.text = String.format(context.resources.getString(R.string.symbols_template), fromsymbol, tosymbol)
                tvPrice.text = price.toString()
                tvLastUpdate.text = String.format(context.resources.getString(R.string.last_update_template),getFormattedTime())
                Picasso.get()
                    .load(getFullImageURL())
                    .into(ivLogoCoin)
            }
        }

    }

    override fun getItemCount() = coinPriceInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder( itemView) {
        val tvSymbols = itemView.tvSymbols
        val tvPrice = itemView.tvPrice
        val tvLastUpdate = itemView.tvLastUpdateLabel
        val ivLogoCoin = itemView.ivLogoCoin
        val dd = itemView
        init {
            itemView.setOnClickListener {
                onCoinClickListener?.onClick(coinPriceInfoList[adapterPosition])
            }
        }
    }

}