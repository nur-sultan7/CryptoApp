package com.nursultan.cryptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.nursultan.cryptoapp.data.database.CoinInfoDao
import com.nursultan.cryptoapp.data.mapper.CoinInfoMapper
import com.nursultan.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinInfoMapper
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        coinInfoDao.deletePriceList()
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

    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService,
        private val mapper: CoinInfoMapper
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context, workerParameters, coinInfoDao, apiService, mapper
            )
        }

    }
}