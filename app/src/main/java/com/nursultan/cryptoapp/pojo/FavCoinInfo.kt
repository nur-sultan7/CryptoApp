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
    conversionSymbol: String,
    isFav:Boolean,
    fSym: String
) : DailyInfoDatum(time, high, low, open, volumeFrom, volumeTo, close, conversionType, conversionSymbol,isFav, fSym) {
    constructor(datum: DailyInfoDatum) : this(
        datum.time,
        datum.high,
        datum.low,
        datum.open,
        datum.volumeFrom,
        datum.volumeTo,
        datum.close,
        datum.conversionType,
        datum.conversionSymbol,
        datum.isFav,
        datum.fSym
    )
}


//) : DailyInfoDatum(
//    time, high, low, open, volumeFrom, volumeTo, close, conversionType, conversionSymbol
//)
//time: Long,
//high: Double,
//low: Double,
//open: Double,
//volumeFrom: Double,
//volumeTo: Double,
//close: Double,
//conversionType: String,
//conversionSymbol: String