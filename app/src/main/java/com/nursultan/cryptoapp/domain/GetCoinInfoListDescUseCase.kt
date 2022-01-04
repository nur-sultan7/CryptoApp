package com.nursultan.cryptoapp.domain

import androidx.lifecycle.LiveData

class GetCoinInfoListDescUseCase(private val repository: CoinRepository) {
    operator fun invoke(): LiveData<List<CoinInfo>> {
        return repository.getCoinInfoListDesc()
    }
}