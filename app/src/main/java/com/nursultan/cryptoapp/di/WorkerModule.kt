package com.nursultan.cryptoapp.di

import com.nursultan.cryptoapp.data.workers.ChildWorkerFactory
import com.nursultan.cryptoapp.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorker(worker: RefreshDataWorker.Factory):ChildWorkerFactory

}