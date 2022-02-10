package com.nursultan.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nursultan.cryptoapp.di.QualifierFSymbol
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.domain.usecases.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListAscUseCase: GetCoinInfoListAscUseCase,
    private val getCoinInfoListDescUseCase: GetCoinInfoListDescUseCase,
    private val insertFavCoinInfoUseCase: InsertFavCoinInfoUseCase,
    private val deleteFavCoinInfoUseCase: deleteFavCoinInfoUseCase,
    coinInfoLoadDataUseCase: GetCoinDataUseCase,
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

    fun insertFavCoin(coinInfo: CoinInfo) {
        viewModelScope.launch {
            insertFavCoinInfoUseCase.invoke(coinInfo)
        }
    }

    fun deleteFavCoin(fSymbol: String) {
        viewModelScope.launch {
            deleteFavCoinInfoUseCase.invoke(fSymbol)
        }
    }
}

