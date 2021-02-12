package com.nursultan.cryptoapp.pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Datum (
    @SerializedName("CoinInfo")
    @Expose
    public val coinInfo: CoinInfo? = null
)