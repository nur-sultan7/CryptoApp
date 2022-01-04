package com.nursultan.cryptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(): LiveData<CoinInfo> {
        return repository.getCoinInfo()
    }
}