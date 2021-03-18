package com.nursultan.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fav_coin_price_info_table")
open class FavCoinInfo(
    type: String? = null,
    market: String? = null,
    fromSymbol: String,
    toSymbol: String? = null,
    price: Double? = null,
    lastUpdate: Long? = null,
    openDay: Double? = null,
    highDay: Double? = null,
    lowDay: Double? = null,
    lastMarket: String? = null,
    volumeHour: Double? = null,
    volumeHourTo: Double? = null,
    imageUrl: String? = null
) : CoinPriceInfo(type, market, fromSymbol, toSymbol, price, lastUpdate, openDay, highDay, lowDay, lastMarket, volumeHour, volumeHourTo, imageUrl) {
    constructor(datum: CoinPriceInfo) : this(
        datum.type,
        datum.market,
        datum.fromSymbol,
        datum.toSymbol,
        datum.price,
        datum.lastUpdate,
        datum.openDay,
        datum.highDay,
        datum.lowDay,
        datum.lastMarket,
        datum.volumeHour,
        datum.volumeHourTo,
        datum.imageUrl
    )
}
