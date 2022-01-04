package com.nursultan.cryptoapp.domain

import androidx.lifecycle.LiveData
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo

interface CoinRepository {
    fun getCoinInfoListAsc(): LiveData<List<CoinInfo>>
    fun getCoinInfoListDesc(): LiveData<List<CoinInfo>>
    fun getCoinInfo(): LiveData<CoinInfo>
    fun getCoinDailyInfoList(): LiveData<List<CoinDailyInfo>>
}