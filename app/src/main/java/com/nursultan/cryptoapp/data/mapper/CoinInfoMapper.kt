package com.nursultan.cryptoapp.data.mapper

import com.google.gson.Gson
import com.nursultan.cryptoapp.data.database.model.CoinDailyInfoDbModel
import com.nursultan.cryptoapp.data.database.model.CoinInfoDbModel
import com.nursultan.cryptoapp.data.database.model.FavCoinInfoDbModel
import com.nursultan.cryptoapp.data.model.CoinDailyInfoDto
import com.nursultan.cryptoapp.data.model.CoinInfoDto
import com.nursultan.cryptoapp.data.model.CoinInfoJsonContainerDto
import com.nursultan.cryptoapp.data.model.CoinNamesListDto
import com.nursultan.cryptoapp.domain.entity.CoinDailyInfo
import com.nursultan.cryptoapp.domain.entity.CoinInfo
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CoinInfoMapper @Inject constructor() {

    fun mapCoinInfoEntityToFavDbModel(coinInfo: CoinInfo) =
        FavCoinInfoDbModel(
            fromSymbol = coinInfo.fromSymbol,
            coinInfo.toSymbol,
            price = coinInfo.price,
            lastUpdate = coinInfo.lastUpdate,
            highDay = coinInfo.highDay,
            lowDay = coinInfo.lowDay,
            lastMarket = coinInfo.lastMarket,
            imageUrl = coinInfo.imageUrl,
        )

    fun mapCoinInfoDtoToModel(dto: CoinInfoDto, isFav: Boolean) =
        CoinInfoDbModel(
            fromSymbol = dto.fromSymbol,
            toSymbol = dto.toSymbol,
            price = dto.price,
            lastMarket = dto.lastMarket,
            lastUpdate = dto.lastUpdate,
            highDay = dto.highDay,
            lowDay = dto.lowDay,
            imageUrl = BASE_IMAGE_URL + dto.imageUrl,
            isFav = isFav
        )

    fun mapCoinInfoDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo = CoinInfo(
        fromSymbol = dbModel.fromSymbol,
        toSymbol = dbModel.toSymbol,
        price = dbModel.price,
        lastMarket = dbModel.lastMarket,
        lastUpdate = convertFromTimestampToTime(dbModel.lastUpdate),
        highDay = dbModel.highDay,
        lowDay = dbModel.lowDay,
        imageUrl = dbModel.imageUrl,
        isFav = dbModel.isFav
        )

    fun mapCoinDailyInfoDbModelToEntity(dbModel: CoinDailyInfoDbModel) = CoinDailyInfo(
        id = dbModel.id,
        fSym = dbModel.fSym,
        time = dbModel.time,
        close = dbModel.close
    )

    fun mapCoinDailyInfoDtoToModel(coinDailyInfoDto: CoinDailyInfoDto, fSymbol: String) =
        CoinDailyInfoDbModel(
            fSym = fSymbol,
            time = coinDailyInfoDto.time,
            close = coinDailyInfoDto.close
        )

    fun mapJsonContainerToCoinInfoList(jsonContainer: CoinInfoJsonContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val priceDataJsonObject = jsonContainer.json ?: return result
        val coinsKeys = priceDataJsonObject.keySet()
        for (coinKey in coinsKeys) {
            val currenciesJsonObject = priceDataJsonObject.getAsJsonObject(coinKey)
            val currenciesKeys = currenciesJsonObject.keySet()
            for (currencyKey in currenciesKeys) {
                val priceInfo = Gson()
                    .fromJson(
                        currenciesJsonObject.getAsJsonObject(currencyKey),
                        CoinInfoDto::class.java
                    )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun mapCoinNamesListToString(coinNamesList: CoinNamesListDto): String {
        return coinNamesList.names?.map { it ->
            it.coinName?.name
        }?.joinToString(",") ?: ""
    }

    private fun convertFromTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val date = Date(timestamp * 1000)
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)

    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}