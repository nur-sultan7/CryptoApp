package com.nursultan.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "fav_coin_price_info_table")
open class FavCoinInfo(
    time: Long,
    high: Double,
    low: Double,
    open: Double,
    volumeFrom: Double,
    volumeTo: Double,
    close: Double,
    conversionType: String,
    conversionSymbol: String
) : DailyInfoDatum(
    type,
    market,
    fromSymbol,
    toSymbol,
    price,
    lastUpdate,
    openDay,
    highDay,
    lowDay,
    lastMarket,
    volumeHour,
    volumeHourTo,
    imageUrl
)