package com.nursultan.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.nursultan.cryptoapp.data.network.ApiFactory
import com.nursultan.cryptoapp.data.database.AppDatabase
import com.nursultan.cryptoapp.data.model.CoinInfoDto
import com.nursultan.cryptoapp.data.model.CoinInfoJsonContainerDto
import com.nursultan.cryptoapp.data.model.CoinDailyInfoDto
import com.nursultan.cryptoapp.data.database.model.FavCoinInfoDbModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceListDesc = db.coinPriceInfoDao().getPriceListDesc()
    val priceListAsc = db.coinPriceInfoDao().getPriceListAsc()
    fun getPriceList(desc: Boolean): LiveData<List<CoinInfoDto>> {
        return if (desc)
            priceListDesc
        else
            priceListAsc
    }

    init {
        loadData()
    }

    fun getCoinPriceInfo(fSym: String): LiveData<CoinInfoDto> {
        return db.coinPriceInfoDao().getCoinInfo(fSym)
    }

    fun getCoinDailyInfo(fSym: String): LiveData<List<CoinDailyInfoDto>> {
        return db.coinPriceInfoDao().getCoinDailyInfo(fSym)
    }

    private fun deleteCoinDailyInfo(fSym: String) {
        Completable.fromAction {
            kotlin.run {
                db.coinPriceInfoDao().deleteCoinDailyInfo(fSym)
            }
        }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("TEST_DELETE", "Success deleted rows ")
                },
                {
                    Log.d("TEST_DELETE", it.message ?: "Error")
                }
            )
    }

    fun insertFavCoin(coinPriceInfoDbModel: FavCoinInfoDbModel) {
        Completable.fromAction {
            kotlin.run {
                db.coinPriceInfoDao().insertFavCoinPriceInfo(coinPriceInfoDbModel)
            }
        }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    Log.d("TEST_INSERT", "Successfully insert fav coin")
                },
                {
                    Log.d("TEST_INSERT", it.message ?: "Error")
                }
            )
    }

    fun deleteFavCoin(coinPriceInfo: CoinInfoDto) {
        Completable.fromAction {
            kotlin.run {
                db.coinPriceInfoDao().deleteFavCoinPriceInfo(coinPriceInfo.fromSymbol)
            }
        }
    }


    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 30)
            .map { it.names?.map { it2 -> it2.coinName?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it!!) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    it.forEach { coin ->
                        coin.isFav = db.coinPriceInfoDao().isItFav(coin.fromSymbol)
                    }
                    db.coinPriceInfoDao().insertCoinInfoList(it)
                    Log.d("TEST_DATA", "Success load $it")
                },
                {
                    Log.d("TEST_DATA", "Failed load ${it.message}")
                }
            )
        compositeDisposable.add(disposable)
    }

    fun loadDailyInfoData(fSym: String) {
        deleteCoinDailyInfo(fSym)
        val disposable = ApiFactory.apiService.getCoinDailyData(fSym)
            .map { it.data.coinsDailyInfo }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    it.forEach { dailyInfo ->
                        dailyInfo.fSym = fSym
                    }
                    db.coinPriceInfoDao().insertDailyInfo(it)
                },
                {

                }
            )
        compositeDisposable.add(disposable)

    }

    private fun getPriceListFromRawData(coinPriceInfoRawData: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = ArrayList<CoinInfoDto>()
        val priceDataJsonObject = coinPriceInfoRawData.json ?: return result
        val coinsKeys = priceDataJsonObject.keySet()
        for (coinKey in coinsKeys) {
            val currenciesJsonObject = priceDataJsonObject.getAsJsonObject(coinKey)
            val currenciesKeys = currenciesJsonObject.keySet()
            for (currencyKey in currenciesKeys) {
                val priceInfo = Gson()
                    .fromJson(
                        currenciesJsonObject.getAsJsonObject(currencyKey),
                        CoinInfoDto::class.java
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

