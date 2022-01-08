package com.nursultan.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nursultan.cryptoapp.data.database.AppDatabase
import com.nursultan.cryptoapp.data.network.ApiFactory
import com.nursultan.cryptoapp.domain.CoinRepository
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.data.mapper.CoinInfoMapper
import kotlinx.coroutines.delay

class CoinRepositoryImp(application: Application) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()

    private val apiService = ApiFactory.apiService

    private val mapper = CoinInfoMapper()

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
        return Transformations.map(coinInfoDao.getListCoinDailyInfo(fSymbol))
        {
            it.map {
                mapper.mapCoinDailyInfoDbModelToEntity(it)
            }
        }
    }

    override suspend fun loadCoinInfoData() {
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


    override suspend fun loadCoinDailyData(fSymbol: String) {
        while (true) {
            try {
                coinInfoDao.deleteCoinDailyInfo(fSymbol)
                val coinDailyData = apiService.getCoinDailyData(fSym = fSymbol)
                val coinDailyInfoList = coinDailyData.data.coinsDailyInfo.map {
                    mapper.mapCoinDailyInfoDtoToModel(it)
                }
                coinInfoDao.insertDailyInfo(coinDailyInfoList)
            } finally {

            }
            delay(5_000)
        }
    }
}