package com.nursultan.cryptoapp.domain.usecases

import com.nursultan.cryptoapp.data.repository.CoinRepositoryImp
import javax.inject.Inject

class DeleteFavCoinInfoUseCase @Inject constructor(
    private val repositoryImp: CoinRepositoryImp
) {
    suspend operator fun invoke(fSymbol: String)
    {
        repositoryImp.deleteFavCoinInfo(fSymbol)
    }
}