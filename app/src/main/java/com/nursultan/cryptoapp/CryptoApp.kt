package com.nursultan.cryptoapp

import android.app.Application
import com.nursultan.cryptoapp.di.DaggerApplicationComponent

class CryptoApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}