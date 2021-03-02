package com.nursultan.cryptoapp.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class DailyInfoResponse(

    @SerializedName("Data")
    @Expose
    val data: DailyInfoData
)