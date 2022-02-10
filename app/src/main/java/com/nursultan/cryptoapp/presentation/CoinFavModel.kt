package com.nursultan.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nursultan.cryptoapp.di.QualifierFSymbol
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.domain.usecases.GetFavCoinUseCase
import com.nursultan.cryptoapp.domain.usecases.deleteFavCoinInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinFavModel @Inject constructor(
    private val getFavCoinUseCase: GetFavCoinUseCase,
    private val deleteFavCoinInfoUseCase: deleteFavCoinInfoUseCase
) : ViewModel() {
    fun getFavCoinList(): LiveData<List<CoinInfo>> {
        return getFavCoinUseCase.invoke()
    }

    fun deleteFavCoin(fSymbol: String) {
        viewModelScope.launch {
            deleteFavCoinInfoUseCase.invoke(fSymbol)
        }
    }
}