package com.nursultan.cryptoapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.nursultan.cryptoapp.data.network.ApiFactory.BASE_IMAGE_URL
import com.nursultan.cryptoapp.utils.convertFromTimestampToTime


@Entity(tableName = "full_price_list")
open class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: Long?,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val imageUrl: String?
)