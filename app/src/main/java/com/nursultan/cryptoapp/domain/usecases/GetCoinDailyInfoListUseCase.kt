package com.nursultan.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.nursultan.cryptoapp.domain.CoinRepository
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo

class GetCoinDailyInfoListUseCase(private val repository: CoinRepository) {
    operator fun invoke(fSymbol: String): LiveData<List<CoinDailyInfo>> {
        return repository.getCoinDailyInfoList(fSymbol)
    }
}