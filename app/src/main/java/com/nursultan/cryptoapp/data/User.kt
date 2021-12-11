package com.nursultan.cryptoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "users")
class User(
    val firstName: String,
    val lastName: String,
    val login: String,
    val password: String,
    val money: Int
) {
    @PrimaryKey(autoGenerate = true)
    lateinit var id: Integer
}