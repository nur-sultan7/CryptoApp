package com.nursultan.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.nursultan.cryptoapp.data.User
import com.nursultan.cryptoapp.database.AppDatabase
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.schedulers.Schedulers

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    fun insertUser(user: User) {
        Completable.fromAction {
            kotlin.run {
                db.coinPriceInfoDao().insertUser(user)
            }
        }
            .subscribeOn(Schedulers.io())
            .subscribe()

    }

    fun getUser(login: String): LiveData<User> {

        return db.coinPriceInfoDao().getUserByLogin(login)

    }
}