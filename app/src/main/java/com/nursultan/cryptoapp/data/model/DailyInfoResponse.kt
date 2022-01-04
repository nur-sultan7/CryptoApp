package com.nursultan.cryptoapp.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class DailyInfoResponse(

    @SerializedName("Data")
    @Expose
    val data: DailyInfoData
)