package com.nursultan.cryptoapp.di

import android.app.Application
import com.nursultan.cryptoapp.presentation.CoinDetailFragment
import com.nursultan.cryptoapp.presentation.CoinPriceListActivity
import com.nursultan.cryptoapp.presentation.CryptoApp
import dagger.BindsInstance
import dagger.Component
@AppScope
@Component(modules = [ViewModelModule::class, DataModule::class, WorkerModule::class])
interface ApplicationComponent {


    fun inject(coinPriceListActivity: CoinPriceListActivity)
    fun inject(coinDetailFragment: CoinDetailFragment)
    fun inject(cryptoApp: CryptoApp)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}