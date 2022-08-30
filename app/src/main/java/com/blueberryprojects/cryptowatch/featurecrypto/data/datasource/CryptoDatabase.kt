package com.blueberryprojects.cryptowatch.featurecrypto.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.CoinData

@Database(entities = [Coin::class, CoinData::class], version = 3)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao
}