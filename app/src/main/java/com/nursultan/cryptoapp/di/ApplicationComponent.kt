package com.nursultan.cryptoapp.di

import android.app.Application
import com.nursultan.cryptoapp.presentation.CoinDetailFragment
import com.nursultan.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class, DataModule::class])
interface ApplicationComponent {


    fun inject(coinPriceListActivity: CoinPriceListActivity)
    fun inject(coinDetailFragment: CoinDetailFragment)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}