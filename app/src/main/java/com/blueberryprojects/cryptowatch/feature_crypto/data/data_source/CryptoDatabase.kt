package com.blueberryprojects.cryptowatch.feature_crypto.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blueberryprojects.cryptowatch.common.Constants.DB_VERSION
import com.blueberryprojects.cryptowatch.feature_crypto.domain.model.Coin

@Database(entities = [Coin::class], version = DB_VERSION)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao
}