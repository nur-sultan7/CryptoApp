package com.nursultan.cryptoapp.di

import androidx.lifecycle.ViewModel
import com.nursultan.cryptoapp.presentation.CoinDetailViewModel
import com.nursultan.cryptoapp.presentation.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(coinViewModel: CoinViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(CoinDetailViewModel::class)
    fun bindCoinDetailViewModel(coinDetailViewModel: CoinDetailViewModel): ViewModel

}