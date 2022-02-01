package com.nursultan.cryptoapp.di

import android.app.Application
import com.nursultan.cryptoapp.data.database.AppDatabase
import com.nursultan.cryptoapp.data.database.CoinInfoDao
import com.nursultan.cryptoapp.data.network.ApiFactory
import com.nursultan.cryptoapp.data.network.ApiService
import com.nursultan.cryptoapp.data.repository.CoinRepositoryImp
import com.nursultan.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {
    @Binds
    fun bindCoinRepository(coinRepositoryImp: CoinRepositoryImp): CoinRepository
    companion object
    {
        @Provides
        fun provideCoinInfoDao(application: Application): CoinInfoDao
        {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
        @Provides
        fun provideApiService():ApiService
        {
            return ApiFactory.apiService
        }
    }
}