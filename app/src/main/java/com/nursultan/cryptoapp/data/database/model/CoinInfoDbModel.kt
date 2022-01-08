package com.nursultan.cryptoapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "full_price_list")
open class CoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: Long?,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val imageUrl: String
)