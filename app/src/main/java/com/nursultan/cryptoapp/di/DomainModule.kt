package com.nursultan.cryptoapp.di

import com.nursultan.cryptoapp.data.repository.CoinRepositoryImp
import com.nursultan.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindCoinRepository(coinRepositoryImp: CoinRepositoryImp): CoinRepository
}