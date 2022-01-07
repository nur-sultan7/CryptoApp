package com.nursultan.cryptoapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.nursultan.cryptoapp.data.model.CoinInfoDto

class CoinDiffUtilCallback(
    private val oldList: List<CoinInfoDto>,
    private val newList: List<CoinInfoDto>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val coinOld = oldList[oldItemPosition]
        val coinNew = newList[newItemPosition]
        return coinOld.fromSymbol == coinNew.fromSymbol
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val coinOld = oldList[oldItemPosition]
        val coinNew = newList[newItemPosition]
        return coinOld.price == coinNew.price
    }
}