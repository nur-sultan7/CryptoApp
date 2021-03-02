package com.nursultan.cryptoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nursultan.cryptoapp.pojo.CoinInfo
import com.nursultan.cryptoapp.pojo.CoinPriceInfo
import com.nursultan.cryptoapp.pojo.DailyInfoDatum

@Dao
interface CoinPriceInfoDao {
    @Query("Select * from full_price_list order by price desc")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    @Query("select * from full_price_list where fromsymbol==:fSym limit 1")
    fun getCoinPriceInfo(fSym : String): LiveData<CoinPriceInfo>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)

    @Insert
    fun insertDailyInfo(listDailyInfo: List<DailyInfoDatum>)

    @Query("select * from daily_info_data where fSym==:fSym")
    fun getCoinDailyInfo(fSym: String): LiveData<List<DailyInfoDatum>>

    @Query("delete from daily_info_data where fSym==:fSym")
    fun deleteSymbolDailyInfo(fSym: String)
}