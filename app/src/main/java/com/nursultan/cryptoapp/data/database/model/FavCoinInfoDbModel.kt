package com.nursultan.cryptoapp.data.database.model

import androidx.room.Entity
import com.nursultan.cryptoapp.data.model.CoinInfoDto

@Entity(tableName = "fav_coin_price_info_table")
open class FavCoinInfoDbModel(
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
) : CoinInfoDto(
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
) {
    constructor(datum: CoinInfoDto) : this(
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
