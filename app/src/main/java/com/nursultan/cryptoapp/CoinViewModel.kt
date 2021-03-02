package com.nursultan.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.nursultan.cryptoapp.api.ApiFactory
import com.nursultan.cryptoapp.database.AppDatabase
import com.nursultan.cryptoapp.pojo.CoinPriceInfo
import com.nursultan.cryptoapp.pojo.CoinPriceInfoRawData
import com.nursultan.cryptoapp.pojo.DailyInfoDatum
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getCoinPriceInfo(fSym: String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getCoinPriceInfo(fSym)
    }

    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 30)
            .map { it.data?.map { it2 -> it2.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it!!) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    db.coinPriceInfoDao().insertPriceList(it)
                    Log.d("TEST_DATA", "Success load $it")
                },
                {
                    Log.d("TEST_DATA", "Failed load ${it.message}")
                }
            )
        compositeDisposable.add(disposable)
    }
     fun loadDailyInfoData(fSym: String)
    {
        val disposable=ApiFactory.apiService.getDailyData(fSym)
            .map { it.data.data }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    it.forEach { dailyInfo->dailyInfo.fSym=fSym }
                    Log.d("DAILY", it.toString())
                }
            ,
                {

                }
            )
        compositeDisposable.add(disposable)

    }

    fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val priceDataJsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinsKeys = priceDataJsonObject.keySet()
        for (coinKey in coinsKeys) {
            val currenciesJsonObject = priceDataJsonObject.getAsJsonObject(coinKey)
            val currenciesKeys = currenciesJsonObject.keySet()
            for (currencyKey in currenciesKeys) {
                val priceInfo = Gson()
                    .fromJson(
                        currenciesJsonObject.getAsJsonObject(currencyKey),
                        CoinPriceInfo::class.java
                    )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

