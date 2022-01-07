package com.nursultan.cryptoapp.data.model

import android.media.Rating
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CoinNameDto(
    @SerializedName("Name")
    @Expose
    val name: String? = null
)