package com.nursultan.cryptoapp.domain.entity

data class CoinDailyInfo(
    var id: Int,
    var fSym:String,
    val time: Long,
    val close: Double,
)