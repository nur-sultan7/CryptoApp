package com.nursultan.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.nursultan.cryptoapp.domain.CoinRepository
import com.nursultan.cryptoapp.domain.entity.CoinInfo

class GetCoinInfoUseCase(private val repository: CoinRepository) {
    operator fun invoke(fSymbol: String): LiveData<CoinInfo> {
        return repository.getCoinInfo(fSymbol)
    }
}