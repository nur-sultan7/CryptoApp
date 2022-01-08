package com.nursultan.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.domain.usecases.GetCoinDailyInfoListUseCase
import com.nursultan.cryptoapp.domain.usecases.GetCoinInfoListAscUseCase
import com.nursultan.cryptoapp.domain.usecases.GetCoinInfoListDescUseCase
import com.nursultan.cryptoapp.domain.usecases.GetCoinInfoUseCase
import com.nursultan.cryptoapp.data.repository.CoinRepositoryImp
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImp(application)

    private val getCoinInfoListAscUseCase = GetCoinInfoListAscUseCase(repository)
    private val getCoinInfoListDescUseCase = GetCoinInfoListDescUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinDailyInfoLListUseCase = GetCoinDailyInfoListUseCase(repository)

    init {
        viewModelScope.launch {
            repository.loadCoinInfoData()
        }
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

