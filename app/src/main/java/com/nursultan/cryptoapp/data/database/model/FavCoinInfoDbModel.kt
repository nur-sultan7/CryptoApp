package com.nursultan.cryptoapp.data.database.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nursultan.cryptoapp.data.model.CoinInfoDto

@Entity(tableName = "fav_coin_price_info_table")
open class FavCoinInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    val id :Int=1,
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
){
    constructor(datum: CoinInfoDto) : this(
        type = datum.type,
        market = datum.market,
        fromSymbol = datum.fromSymbol,
        toSymbol = datum.toSymbol,
        price = datum.price,
        lastUpdate = datum.lastUpdate,
        openDay = datum.openDay,
        highDay = datum.highDay,
        lowDay = datum.lowDay,
        lastMarket = datum.lastMarket,
        volumeHour = datum.volumeHour,
        volumeHourTo = datum.volumeHourTo,
        imageUrl = datum.imageUrl
    )
}
