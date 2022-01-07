package com.nursultan.cryptoapp.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class CoinsDailyInfoListContainerDto(
    @SerializedName("Data")
    @Expose
    val data: CoinDailyInfoListDto
)