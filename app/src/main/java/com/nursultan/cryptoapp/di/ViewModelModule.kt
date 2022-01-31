package com.nursultan.cryptoapp.di

import androidx.lifecycle.ViewModel
import com.nursultan.cryptoapp.presentation.CoinViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(coinViewModel: CoinViewModel):ViewModel

}