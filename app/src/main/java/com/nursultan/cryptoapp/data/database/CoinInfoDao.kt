package com.nursultan.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nursultan.cryptoapp.data.database.model.CoinDailyInfoDbModel
import com.nursultan.cryptoapp.data.database.model.CoinInfoDbModel
import com.nursultan.cryptoapp.data.database.model.FavCoinInfoDbModel
import com.nursultan.cryptoapp.data.model.CoinInfoDto
import com.nursultan.cryptoapp.data.model.CoinDailyInfoDto

@Dao
interface CoinInfoDao {


    @Query("select * from full_price_list order by price desc")
    fun getPriceListDesc(): LiveData<List<CoinInfoDbModel>>

    @Query("select * from full_price_list order by price asc")
    fun getPriceListAsc(): LiveData<List<CoinInfoDbModel>>

    @Query("select * from full_price_list where fromSymbol==:fSym limit 1")
    fun getCoinInfo(fSym: String): LiveData<CoinInfoDbModel>

    @Query("delete from full_price_list ")
    suspend fun deletePriceList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinInfoList(priceList: List<CoinInfoDbModel>)

    @Insert
    fun insertCoinDailyInfoList(listCoinDailyInfo: List<CoinDailyInfoDbModel>)

    @Query("select * from daily_info_data where fSym==:fSym order by time asc ")
    fun getCoinDailyInfoList(fSym: String): LiveData<List<CoinDailyInfoDbModel>>

    @Query("delete from daily_info_data where fSym==:fSym")
    suspend fun deleteCoinDailyInfo(fSym: String)

    @Query("select * from fav_coin_info_table")
    fun getFavCoinList(): LiveData<List<FavCoinInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCoinInfo(favCoin: FavCoinInfoDbModel)

    @Query("delete from fav_coin_info_table where fromSymbol==:fSym")
    suspend fun deleteFavCoinInfo(fSym: String)

    @Query("select exists (select * from fav_coin_info_table where fromSymbol==:fSym limit 1)")
    fun isFav(fSym: String): Boolean
}