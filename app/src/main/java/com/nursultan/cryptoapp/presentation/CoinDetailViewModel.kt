package com.nursultan.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nursultan.cryptoapp.di.QualifierFSymbol
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.usecases.GetCoinDailyInfoListUseCase
import com.nursultan.cryptoapp.domain.usecases.GetCoinInfoUseCase
import com.nursultan.cryptoapp.domain.usecases.LoadCoinDailyInfoUseCase
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(
    private val loadCoinDailyInfoUseCase: LoadCoinDailyInfoUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getCoinDailyInfoLListUseCase: GetCoinDailyInfoListUseCase,
    @QualifierFSymbol private val fSym: String
) : ViewModel() {

    init {
        loadCoinDailyInfoUseCase.invoke(fSym)
    }

    fun getCoinInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)


    fun getCoinDailyInfo(fSym: String): LiveData<List<CoinDailyInfo>> =
        getCoinDailyInfoLListUseCase.invoke(fSym)
}