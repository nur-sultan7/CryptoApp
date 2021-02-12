package com.nursultan.cryptoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nursultan.cryptoapp.pojo.CoinInfo
import com.nursultan.cryptoapp.pojo.CoinPriceInfo

@Dao
interface CoinPriceInfoDao {
    @Query("Select * from full_price_list order by lastupdate")
    fun getPriceList(): LiveData<List<CoinPriceInfo>>

    @Query("select * from full_price_list where fromsymbol==:fSym limit 1")
    fun getCoinPriceInfo(fSym : String): LiveData<CoinPriceInfo>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfo>)
}