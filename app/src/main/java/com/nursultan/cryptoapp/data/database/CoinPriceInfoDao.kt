package com.nursultan.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nursultan.cryptoapp.data.model.CoinPriceInfo
import com.nursultan.cryptoapp.data.model.DailyInfoDatum
import com.nursultan.cryptoapp.data.model.FavCoinInfo

@Dao
interface CoinPriceInfoDao {


    @Query("select * from full_price_list order by price desc")
    fun getPriceListDesc(): LiveData<List<CoinPriceInfo>>

    @Query("select * from full_price_list order by price asc")
    fun getPriceListAsc(): LiveData<List<CoinPriceInfo>>

    @Query("select * from full_price_list where fromSymbol==:fSym limit 1")
    fun getCoinPriceInfo(fSym: String): LiveData<CoinPriceInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)

    @Insert
    fun insertDailyInfo(listDailyInfo: List<DailyInfoDatum>)

    @Query("select * from daily_info_data where fSym==:fSym order by time ")
    fun getCoinDailyInfo(fSym: String): LiveData<List<DailyInfoDatum>>

    @Query("delete from daily_info_data where fSym==:fSym")
    fun deleteSymbolDailyInfo(fSym: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavCoinPriceInfo(favCoin: FavCoinInfo)

    @Query("delete from fav_coin_price_info_table where fromSymbol==:fSym")
    fun deleteFavCoinPriceInfo(fSym: String)

    @Query("select exists (select * from fav_coin_price_info_table where fromSymbol==:fSym limit 1)")
    fun isItFav(fSym: String): Boolean
}