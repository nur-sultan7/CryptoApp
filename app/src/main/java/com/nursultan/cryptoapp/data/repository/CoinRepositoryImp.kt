package com.nursultan.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.nursultan.cryptoapp.data.database.CoinInfoDao
import com.nursultan.cryptoapp.data.mapper.CoinInfoMapper
import com.nursultan.cryptoapp.data.workers.RefreshCoinDailyInfoWorker
import com.nursultan.cryptoapp.data.workers.RefreshDataWorker
import com.nursultan.cryptoapp.domain.CoinRepository
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import javax.inject.Inject

class CoinRepositoryImp @Inject constructor(
    private val application: Application,
    private val mapper: CoinInfoMapper,
    private val coinInfoDao: CoinInfoDao
) : CoinRepository {

    override fun getCoinInfoListAsc(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceListAsc())
        { list ->
            list.map {
                mapper.mapCoinInfoDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfoListDesc(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceListDesc())
        { list ->
            list.map {
                mapper.mapCoinInfoDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getCoinInfo(fSymbol))
        {
            mapper.mapCoinInfoDbModelToEntity(it)
        }
    }

    override fun getCoinDailyInfoList(fSymbol: String): LiveData<List<CoinDailyInfo>> {
        return Transformations.map(coinInfoDao.getCoinDailyInfoList(fSymbol))
        {
            it.map {
                mapper.mapCoinDailyInfoDbModelToEntity(it)
            }
        }
    }

    override fun loadCoinInfoData() {
        val workerManager = WorkManager.getInstance(application)
        workerManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }


    override fun loadCoinDailyData(fSymbol: String) {
        val workerManager = WorkManager.getInstance(application)
        workerManager.enqueueUniqueWork(
            RefreshCoinDailyInfoWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshCoinDailyInfoWorker.makeRequest()
        )
    }
}