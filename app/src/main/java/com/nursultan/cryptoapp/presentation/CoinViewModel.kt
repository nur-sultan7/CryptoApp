package com.nursultan.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.domain.usecases.*
import javax.inject.Inject

class CoinViewModel @Inject constructor(

    private val getCoinInfoListAscUseCase: GetCoinInfoListAscUseCase,
    private val getCoinInfoListDescUseCase: GetCoinInfoListDescUseCase,
    coinInfoLoadDataUseCase: GetCoinDataUseCase,
    private val loadCoinDailyInfoUseCase: LoadCoinDailyInfoUseCase

) : ViewModel() {
    init {
        coinInfoLoadDataUseCase.invoke()
    }

    fun getCoinInfoList(desc: Boolean): LiveData<List<CoinInfo>> {
        return if (desc)
            getCoinInfoListDescUseCase.invoke()
        else
            getCoinInfoListAscUseCase.invoke()
    }








    private fun deleteCoinDailyInfo(fSym: String) {

    }

    fun insertFavCoin(coinPriceInf: CoinInfo) {

    }

    fun deleteFavCoin(coinPriceInfo: CoinInfo) {

    }


}

