package com.nursultan.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.nursultan.cryptoapp.domain.CoinRepository
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import javax.inject.Inject

class GetCoinInfoListAscUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): LiveData<List<CoinInfo>> {
        return repository.getCoinInfoListAsc()
    }
}