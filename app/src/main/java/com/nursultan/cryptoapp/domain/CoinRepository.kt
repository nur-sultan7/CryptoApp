package com.nursultan.cryptoapp.domain

import androidx.lifecycle.LiveData

interface CoinRepository {
    fun getCoinInfoListAsc(): LiveData<List<CoinInfo>>
    fun getCoinInfoListDesc(): LiveData<List<CoinInfo>>
    fun getCoinInfo(): LiveData<CoinInfo>
    fun getCoinDailyInfoList(): LiveData<List<CoinDailyInfo>>
}