package com.nursultan.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.like.LikeButton
import com.nursultan.cryptoapp.R
import com.nursultan.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.utils.convertFromTimestampToTime
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coin_price_info.view.*

class CoinInfoAdapter(private val context: Context) :
    RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {
    var coinPriceInfoList = listOf<CoinInfo>()

    private var onCoinClickListener: OnCoinClickListener? = null
    var onFavClickListener: OnFavClickListener? = null


    interface OnCoinClickListener {
        fun onClick(coinPriceInfo: CoinInfo)
    }

    interface OnFavClickListener {
        fun onClick(coinPriceInfo: CoinInfo, isFav: Boolean)
    }

    fun setOnCoinClickListener(onClickListener: OnCoinClickListener) {
        this.onCoinClickListener = onClickListener
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
                tvSymbols.text = String.format(
                    context.resources.getString(R.string.symbols_template),
                    fromSymbol,
                    toSymbol
                )
                tvPrice.text = price.toString().take(8)
                tvLastUpdate.text = String.format(
                    context.resources.getString(R.string.last_update_template),
                    convertFromTimestampToTime(lastUpdate)
                )
                btnFav.isLiked = isFav
                Picasso.get()
                    .load(BASE_IMAGE_URL + imageUrl)
                    .into(ivLogoCoin)
            }
        }

    }

    override fun getItemCount() = coinPriceInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSymbols: TextView = itemView.tvSymbols10
        val tvPrice: TextView = itemView.tvPrice10
        val tvLastUpdate: TextView = itemView.tvLastUpdateLabel
        val ivLogoCoin: ImageView = itemView.ivLogoCoin10
        val btnFav: LikeButton = itemView.btnFav

        init {
            itemView.setOnClickListener {
                onCoinClickListener?.onClick(coinPriceInfoList[absoluteAdapterPosition])
            }
            btnFav.setOnClickListener {
                onFavClickListener?.onClick(coinPriceInfoList[absoluteAdapterPosition], btnFav.isLiked)
                btnFav.isLiked = !btnFav.isLiked
            }
        }
    }

}