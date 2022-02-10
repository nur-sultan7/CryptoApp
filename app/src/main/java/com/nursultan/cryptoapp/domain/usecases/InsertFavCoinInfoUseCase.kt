package com.nursultan.cryptoapp.domain.usecases

import com.nursultan.cryptoapp.data.repository.CoinRepositoryImp
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import javax.inject.Inject

class InsertFavCoinInfoUseCase @Inject constructor(private val repositoryImp: CoinRepositoryImp) {
    suspend operator fun invoke(coinInfo: CoinInfo) {
        repositoryImp.insertFavCoinInfo(coinInfo)
    }
}