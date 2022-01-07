package com.nursultan.cryptoapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.nursultan.cryptoapp.data.database.AppDatabase
import com.nursultan.cryptoapp.data.network.ApiFactory
import com.nursultan.cryptoapp.domain.CoinRepository
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import com.nursultan.cryptoapp.mapper.CoinInfoMapper
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.delay

class CoinRepositoryImp(private val application: Application): CoinRepository {

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
            val topCoins = apiService.getTopCoinsInfo(limit = 50)
            val fSymbols = mapper.mapCoinNamesListToString(topCoins)
            val jsonContainer = apiService.getFullPriceList(fSyms = fSymbols)
            val coinInfoListDto = mapper.mapJsonContainerToCoinInfoList(jsonContainer)
            coinInfoDao.insertCoinInfoList(coinInfoListDto.map {
                mapper.mapCoinInfoDtoToModel(it)
            })
            delay(5_000)
        }
    }

    override suspend fun loadCoinDailyData(fSymbol: String) {
        while (true) {
            coinInfoDao.deleteCoinDailyInfo(fSymbol)
            val coinDailyData = apiService.getCoinDailyData(fSym = fSymbol)
            val coinDailyInfoList = coinDailyData.data.coinsDailyInfo.map {
                mapper.mapCoinDailyInfoDtoToModel(it)
            }
            coinInfoDao.insertDailyInfo(coinDailyInfoList)
            delay(5_000)
        }
    }
}