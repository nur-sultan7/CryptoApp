package com.nursultan.cryptoapp.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.nursultan.cryptoapp.data.database.CoinInfoDao
import com.nursultan.cryptoapp.data.mapper.CoinInfoMapper
import com.nursultan.cryptoapp.data.network.ApiFactory
import com.nursultan.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshCoinDailyInfoWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinInfoMapper,
    private val fSymbol: String
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                coinInfoDao.deleteCoinDailyInfo(fSymbol)
                val coinDailyData = apiService.getCoinDailyData(fSym = fSymbol)
                val coinDailyInfoList = coinDailyData.data.coinsDailyInfo.map {
                    mapper.mapCoinDailyInfoDtoToModel(it)
                }
                coinInfoDao.insertCoinDailyInfoList(coinDailyInfoList)
            } finally {

            }
            delay(5_000)
        }
    }

    companion object {
        const val NAME = "RefreshCoinDailyInfoWorker"
        fun makeRequest() =
            OneTimeWorkRequestBuilder<RefreshCoinDailyInfoWorker>().build()
    }

    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService,
        private val mapper: CoinInfoMapper,
        private val fSymbol: String
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshCoinDailyInfoWorker(
                context, workerParameters, coinInfoDao, apiService, mapper, fSymbol
            )
        }

    }
}