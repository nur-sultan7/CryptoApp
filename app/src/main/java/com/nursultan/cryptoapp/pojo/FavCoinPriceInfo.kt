package com.nursultan.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
@Entity(tableName = "fav_coin_price_info_table")
class FavCoinPriceInfo(
    coinPriceInfo: CoinPriceInfo
) : CoinPriceInfo(
    coinPriceInfo.type,
    coinPriceInfo.market,
    coinPriceInfo.fromsymbol,
    coinPriceInfo.tosymbol,
    coinPriceInfo.price,
    coinPriceInfo.lastupdate,
    coinPriceInfo.openday,
    coinPriceInfo.highday,
    coinPriceInfo.lowday,
    coinPriceInfo.lastmarket,
    coinPriceInfo.holumehour,
    coinPriceInfo.volumehourto,
    coinPriceInfo.imageUrl
) {
}