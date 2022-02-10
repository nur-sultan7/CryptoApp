package com.nursultan.cryptoapp.di

import com.nursultan.cryptoapp.presentation.CoinDetailFragment
import com.nursultan.cryptoapp.presentation.CoinFavActivity
import com.nursultan.cryptoapp.presentation.CoinPriceListActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface ActivityComponent {

    fun inject(coinPriceListActivity: CoinPriceListActivity)
    fun inject(coinDetailFragment: CoinDetailFragment)
    fun inject(coinFavActivity: CoinFavActivity)

    @Subcomponent.Factory
    interface CoinDetailComponentFactory {
        fun create(
            @BindsInstance @QualifierFSymbol fSymbol: String = ""
        ): ActivityComponent
    }
}