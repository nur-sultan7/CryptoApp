package com.nursultan.cryptoapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

object ApiFactory {
    private const val BASE_URL = "https://min-api.cryptocompare.com/data/"

    private val retrofit= Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}