package com.nursultan.cryptoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


open class CoinInfoDto(

    @SerializedName("TYPE")
    @Expose
    var type: String? = null,

    @SerializedName("MARKET")
    @Expose
    var market: String? = null,

    @SerializedName("FROMSYMBOL")
    @Expose
    var fromSymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    var toSymbol: String? = null,

    @SerializedName("PRICE")
    @Expose
    var price: Double? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    var lastUpdate: Long? = null,

    @SerializedName("OPENDAY")
    @Expose
    var openDay: Double? = null,

    @SerializedName("HIGHDAY")
    @Expose
    var highDay: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
    var lowDay: Double? = null,

    @SerializedName("LASTMARKET")
    @Expose
    var lastMarket: String? = null,

    @SerializedName("VOLUMEHOUR")
    @Expose
    var volumeHour: Double? = null,

    @SerializedName("VOLUMEHOURTO")
    @Expose
    var volumeHourTo: Double? = null,

    @SerializedName("IMAGEURL")
    @Expose
    var imageUrl: String? = null,

)