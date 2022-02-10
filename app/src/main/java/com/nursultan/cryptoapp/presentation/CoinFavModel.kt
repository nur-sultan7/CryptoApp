package com.nursultan.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.domain.usecases.GetFavCoinUseCase
import javax.inject.Inject

class CoinFavModel @Inject constructor(
    private val getFavCoinUseCase: GetFavCoinUseCase
) : ViewModel() {
    fun getFavCoinList(): LiveData<List<CoinInfo>> {
        return getFavCoinUseCase.invoke()
    }

    fun deleteFavCoin(coinInfo: CoinInfo) {

    }
}