package com.nursultan.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.nursultan.cryptoapp.api.ApiFactory
import com.nursultan.cryptoapp.database.AppDatabase
import com.nursultan.cryptoapp.pojo.Datum
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class CoinViewModel(application: Application): AndroidViewModel(application) {
    private val db=AppDatabase.getInstance(application)
    private val compositeDisposable=CompositeDisposable()

    val priceList=db.coinPriceInfoDao().getPriceList()

    fun getData()
    {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 50)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val coinsString = it.data?.map { it2 -> it2.coinInfo?.name }?.joinToString (",")
                    Log.d("TEST_DATA", (coinsString).toString())
                }
                    ,
                {

                }
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

