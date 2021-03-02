package com.nursultan.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Entity(tableName = "daily_info_data")
data class DailyInfoDatum(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var fSym:String="",

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
    val conversionSymbol: String
)