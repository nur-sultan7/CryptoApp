package com.nursultan.cryptoapp.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.nursultan.cryptoapp.data.database.CoinInfoDao
import com.nursultan.cryptoapp.data.mapper.CoinInfoMapper
import com.nursultan.cryptoapp.data.network.ApiService
import javax.inject.Inject

class RefreshDataWorkerFactory
    @Inject constructor(
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val mapper: CoinInfoMapper
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return RefreshDataWorker(
            appContext,
            workerParameters,
            coinInfoDao,
            apiService,
            mapper
        )
    }
}