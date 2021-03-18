package com.nursultan.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.nursultan.cryptoapp.api.ApiFactory.BASE_IMAGE_URL
import com.nursultan.cryptoapp.utils.convertFromTimestampToTime


@Entity(tableName = "full_price_list")
open class CoinPriceInfo(

    @SerializedName("TYPE")
     @Expose
      var type: String? = null,

    @SerializedName("MARKET")
     @Expose
      var market: String? = null,

    @PrimaryKey
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
      var imageUrl: String? = null


)
{

    fun getFormattedTime():String
    {
        return convertFromTimestampToTime(lastUpdate)
    }
    fun getFullImageURL(): String {
        return BASE_IMAGE_URL+imageUrl
    }
}