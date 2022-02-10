package com.nursultan.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.nursultan.cryptoapp.data.repository.CoinRepositoryImp
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import javax.inject.Inject

class GetFavCoinUseCase @Inject constructor(private val repositoryImp: CoinRepositoryImp) {
    operator fun invoke(): LiveData<List<CoinInfo>> {
        return repositoryImp.getFavCoinList()
    }
}