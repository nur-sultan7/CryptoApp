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
import com.nursultan.cryptoapp.di.QualifierFSymbol
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshCoinDailyInfoWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinInfoMapper,

) : CoroutineWorker(context, workerParameters) {
    private val fSymbol: String ="BTC"
    override suspend fun doWork(): Result {

            try {
                coinInfoDao.deleteCoinDailyInfo(fSymbol)
                val coinDailyData = apiService.getCoinDailyData(fSym = fSymbol)
                val coinDailyInfoList = coinDailyData.data.coinsDailyInfo.map {
                    mapper.mapCoinDailyInfoDtoToModel(it, fSymbol)
                }
                coinInfoDao.insertCoinDailyInfoList(coinDailyInfoList)
            } finally {


        }
        return Result.success()
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
    ) : ChildWorkerFactory {
        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshCoinDailyInfoWorker(
                context, workerParameters, coinInfoDao, apiService, mapper
            )
        }

    }
}