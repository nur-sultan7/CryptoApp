package com.nursultan.cryptoapp.data.database.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nursultan.cryptoapp.data.model.CoinInfoDto

@Entity(tableName = "fav_coin_info_table")
open class FavCoinInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: Double?,
    val lastUpdate: String,
    val highDay: Double?,
    val lowDay: Double?,
    val lastMarket: String?,
    val imageUrl: String,
)
