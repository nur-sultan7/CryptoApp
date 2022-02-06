package com.nursultan.cryptoapp.data.workers

import android.content.Context
import androidx.work.*
import com.nursultan.cryptoapp.data.database.CoinInfoDao
import com.nursultan.cryptoapp.data.mapper.CoinInfoMapper
import com.nursultan.cryptoapp.data.network.ApiService
import java.lang.RuntimeException
import javax.inject.Inject

class RefreshCoinDailyInfoWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinInfoMapper,

    ) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val fSymbol = inputData.getString(DATA_KEY) ?: throw RuntimeException("FSymbol is null")
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
        const val DATA_KEY = "fSymbol"
        fun makeRequest(fSymbol: String) =
            OneTimeWorkRequestBuilder<RefreshCoinDailyInfoWorker>()
                .setInputData(workDataOf(DATA_KEY to fSymbol))
                .build()
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