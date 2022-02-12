package com.nursultan.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nursultan.cryptoapp.di.QualifierFSymbol
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.domain.usecases.GetFavCoinUseCase
import com.nursultan.cryptoapp.domain.usecases.InsertFavCoinInfoUseCase
import com.nursultan.cryptoapp.domain.usecases.deleteFavCoinInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinFavViewModel @Inject constructor(
    private val getFavCoinUseCase: GetFavCoinUseCase,
    private val deleteFavCoinInfoUseCase: deleteFavCoinInfoUseCase,
    private val insertFavCoinInfoUseCase: InsertFavCoinInfoUseCase
) : ViewModel() {
    fun getFavCoinList(): LiveData<List<CoinInfo>> {
        return getFavCoinUseCase.invoke()
    }

    fun deleteFavCoin(fSymbol: String) {
        viewModelScope.launch {
            deleteFavCoinInfoUseCase.invoke(fSymbol)
        }
    }
    fun addToFavCoin(coinInfo: CoinInfo)
    {
        viewModelScope.launch {
            insertFavCoinInfoUseCase.invoke(coinInfo)
        }
    }

}