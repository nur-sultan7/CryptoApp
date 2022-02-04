package com.nursultan.cryptoapp.di

import com.nursultan.cryptoapp.presentation.CoinDetailFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface CoinDetailSubcomponent {
    fun inject(coinDetailFragment: CoinDetailFragment)

    @Subcomponent.Factory
    interface CoinDetailComponentFactory
    {
        fun create(
            @BindsInstance fSymbol: String
        ):CoinDetailSubcomponent
    }
}