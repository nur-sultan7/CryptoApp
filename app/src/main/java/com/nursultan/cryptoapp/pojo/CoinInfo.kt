package com.nursultan.cryptoapp.pojo

import android.media.Rating
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


 data class CoinInfo(
    @SerializedName("Id")
    @Expose
    public val id: String? = null,

    @SerializedName("Name")
    @Expose
    public val name: String? = null,

    @SerializedName("FullName")
    @Expose
    public val fullName: String? = null,
     

    @SerializedName("ImageUrl")
    @Expose
    public val imageUrl: String? = null,

    @SerializedName("Url")
    @Expose
    public val url: String? = null
)