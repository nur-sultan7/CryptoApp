package com.nursultan.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nursultan.cryptoapp.data.repository.CoinRepositoryImp
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.domain.usecases.*
import javax.inject.Inject

class CoinViewModel @Inject constructor (
    repositoryImp: CoinRepositoryImp) : ViewModel() {

    private val getCoinInfoListAscUseCase = GetCoinInfoListAscUseCase(repositoryImp)
    private val getCoinInfoListDescUseCase = GetCoinInfoListDescUseCase(repositoryImp)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repositoryImp)
    private val getCoinDailyInfoLListUseCase = GetCoinDailyInfoListUseCase(repositoryImp)
    private val coinInfoLoadDataUseCase = CoinInfoLoadDataUseCase(repositoryImp)

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

    private fun deleteCoinDailyInfo(fSym: String) {

    }

    fun insertFavCoin(coinPriceInf: CoinInfo) {

    }

    fun deleteFavCoin(coinPriceInfo: CoinInfo) {

    }



}

