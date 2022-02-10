package com.nursultan.cryptoapp.data.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class CoinDailyInfoDto(

    @SerializedName("time")
    @Expose
    val time: Long,

    @SerializedName("high")
    @Expose
    val high: Double,

    @SerializedName("low")
    @Expose
    val low: Double,

    @SerializedName("open")
    @Expose
    val open: Double,

    @SerializedName("volumefrom")
    @Expose
    val volumeFrom: Double,

    @SerializedName("volumeto")
    @Expose
    val volumeTo: Double,

    @SerializedName("close")
    @Expose
    val close: Double,

    @SerializedName("conversionType")
    @Expose
    val conversionType: String,

    @SerializedName("conversionSymbol")
    @Expose
    val conversionSymbol: String,
    var fSym:String=""
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    constructor(
        time: Long,
        high: Double,
        low: Double,
        open: Double,
        volumeFrom: Double,
        volumeTo: Double,
        close: Double,
        conversionType: String,
        conversionSymbol: String,
        fSym: String,
        isFav: Boolean
    ) : this(time, high, low, open, volumeFrom, volumeTo, close, conversionType, conversionSymbol,fSym)
}