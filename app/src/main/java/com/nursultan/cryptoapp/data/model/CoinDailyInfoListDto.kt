package com.nursultan.cryptoapp.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class CoinDailyInfoListDto(

    @SerializedName("Data")
    @Expose
    var coinsDailyInfo: MutableList<CoinDailyInfoDto>

)