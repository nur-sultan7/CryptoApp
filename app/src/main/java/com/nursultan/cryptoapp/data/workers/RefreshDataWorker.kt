package com.nursultan.cryptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.nursultan.cryptoapp.data.database.AppDatabase
import com.nursultan.cryptoapp.data.mapper.CoinInfoMapper
import com.nursultan.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val coinInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()

    private val apiService = ApiFactory.apiService

    private val mapper = CoinInfoMapper()

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSymbols = mapper.mapCoinNamesListToString(topCoins)
                val jsonContainer = apiService.getFullPriceList(fSyms = fSymbols)
                val coinInfoListDto = mapper.mapJsonContainerToCoinInfoList(jsonContainer)
                coinInfoDao.insertCoinInfoList(coinInfoListDto.map {
                    mapper.mapCoinInfoDtoToModel(it)
                })
            } catch (e: Exception) {
            }
            delay(5_000)
        }
    }

    companion object {
        const val NAME = "RefreshDataWorker"
        fun makeRequest() =
            OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
    }
}