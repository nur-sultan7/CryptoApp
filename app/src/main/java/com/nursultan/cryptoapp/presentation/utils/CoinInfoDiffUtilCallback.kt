package com.nursultan.cryptoapp.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.nursultan.cryptoapp.domain.entity.CoinInfo

object  CoinInfoDiffUtilCallback : DiffUtil.ItemCallback<CoinInfo>() {

    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromSymbol == newItem.fromSymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}