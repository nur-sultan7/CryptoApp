package com.nursultan.cryptoapp.domain.usecases

import com.nursultan.cryptoapp.domain.CoinRepository

class CoinInfoLoadDataUseCase(private val repository: CoinRepository) {
    private suspend operator fun invoke() = repository.loadCoinInfoData()
}