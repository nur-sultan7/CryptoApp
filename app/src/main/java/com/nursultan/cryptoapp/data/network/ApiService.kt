package com.nursultan.cryptoapp.data.network

import com.nursultan.cryptoapp.data.model.CoinNamesListDto
import com.nursultan.cryptoapp.data.model.CoinInfoJsonContainerDto
import com.nursultan.cryptoapp.data.model.CoinsDailyInfoListContainerDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/histoday")
    suspend fun getCoinDailyData(
        @Query(QUERY_PARAM_FROM_SYMBOL) fSym:String,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String= CURRENCY,
        @Query(QUERY_PARAM_LIMIT) limit: Int=7
    ):CoinsDailyInfoListContainerDto

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API) apiKey:String="",
        @Query(QUERY_PARAM_LIMIT) limit:Int =10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinNamesListDto


    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API) apiKey: String="",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String= CURRENCY

    ): CoinInfoJsonContainerDto


    companion object
    {
        private const val QUERY_PARAM_API="api_key"
        private const val QUERY_PARAM_LIMIT="limit"
        private const val QUERY_PARAM_TO_SYMBOL="tsym"
        private const val QUERY_PARAM_FROM_SYMBOL="fsym"
        private const val QUERY_PARAM_TO_SYMBOLS="tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS="fsyms"
        private const val CURRENCY ="USD"

    }
}