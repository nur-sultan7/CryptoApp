package com.nursultan.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import javax.inject.Inject

class CoinFavModel @Inject constructor() : ViewModel() {
    fun getFavCoinList(): LiveData<List<CoinInfo>> {
        TODO()
    }

    fun deleteFavCoin(coinInfo: CoinInfo) {

    }
}