package com.nursultan.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.nursultan.cryptoapp.data.workers.WorkerFactory
import com.nursultan.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CryptoApp : Application(), Configuration.Provider {
    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
    @Inject
    lateinit var workerFactory: WorkerFactory

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory).build()
    }
}