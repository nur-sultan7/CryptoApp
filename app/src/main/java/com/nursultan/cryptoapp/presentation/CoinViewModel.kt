package com.nursultan.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nursultan.cryptoapp.data.repository.CoinRepositoryImp
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.domain.usecases.*
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinDailyInfoLListUseCase: GetCoinDailyInfoListUseCase,
    private val getCoinInfoListAscUseCase: GetCoinInfoListAscUseCase,
    private val getCoinInfoListDescUseCase: GetCoinInfoListDescUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    coinInfoLoadDataUseCase: GetCoinDataUseCase,
    private val loadCoinDailyInfo: LoadCoinDailyInfo

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


    fun getCoinInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)

    fun getCoinDailyInfo(fSym: String): LiveData<List<CoinDailyInfo>> =
        getCoinDailyInfoLListUseCase.invoke(fSym)

    fun loadCoinDailyInfo(fSym: String) {
        loadCoinDailyInfo.invoke(fSym)
    }

    private fun deleteCoinDailyInfo(fSym: String) {

    }

    fun insertFavCoin(coinPriceInf: CoinInfo) {

    }

    fun deleteFavCoin(coinPriceInfo: CoinInfo) {

    }


}

