package com.nursultan.cryptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinDailyInfoListUseCase(private val repository: CoinRepository) {
    operator fun invoke(): LiveData<List<CoinDailyInfo>> {
        return repository.getCoinDailyInfoList()
    }
}