package com.nursultan.cryptoapp.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class DailyInfoData(

    @SerializedName("Data")
    @Expose
    var data: MutableList<DailyInfoDatum>

)