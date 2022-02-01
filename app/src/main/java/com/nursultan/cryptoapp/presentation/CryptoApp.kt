package com.nursultan.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.nursultan.cryptoapp.data.database.AppDatabase
import com.nursultan.cryptoapp.data.mapper.CoinInfoMapper
import com.nursultan.cryptoapp.data.network.ApiFactory
import com.nursultan.cryptoapp.data.workers.RefreshDataWorkerFactory
import com.nursultan.cryptoapp.di.DaggerApplicationComponent

class CryptoApp : Application(), Configuration.Provider {
    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinInfoMapper()
                )
            ).build()
    }
}