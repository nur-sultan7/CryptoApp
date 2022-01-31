package com.nursultan.cryptoapp.di

import android.app.Application
import com.nursultan.cryptoapp.presentation.CoinPriceListActivity
import dagger.Binds
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface ApplicationComponent {


    fun inject(coinPriceListActivity: CoinPriceListActivity)

    @Component.Factory
    interface ApplicationComponentFactory
    {
        fun create(
            @BindsInstance application: Application
        ):ApplicationComponent
    }
}