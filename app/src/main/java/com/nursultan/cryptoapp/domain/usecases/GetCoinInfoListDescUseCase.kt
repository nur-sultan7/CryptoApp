package com.nursultan.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.nursultan.cryptoapp.domain.CoinRepository
import com.nursultan.cryptoapp.domain.entity.CoinInfo

class GetCoinInfoListDescUseCase(private val repository: CoinRepository) {
    operator fun invoke(): LiveData<List<CoinInfo>> {
        return repository.getCoinInfoListDesc()
    }
}