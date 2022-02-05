package com.nursultan.cryptoapp.domain.usecases

import com.nursultan.cryptoapp.domain.CoinRepository
import javax.inject.Inject

class LoadCoinDailyInfoUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(fSymbol: String) {
        repository.loadCoinDailyData(fSymbol)
    }
}