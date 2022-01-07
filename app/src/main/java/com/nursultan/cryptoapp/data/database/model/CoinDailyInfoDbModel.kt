package com.nursultan.cryptoapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "daily_info_data")
open class CoinDailyInfoDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var fSym:String,
    val time: Long,
    val close: Double,
)