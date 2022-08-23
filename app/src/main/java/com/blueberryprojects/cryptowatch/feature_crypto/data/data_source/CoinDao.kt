package com.blueberryprojects.cryptowatch.feature_crypto.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.blueberryprojects.cryptowatch.feature_crypto.domain.model.Coin

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin")
    fun getAllCoins(): List<Coin>

    @Query("SELECT * FROM coin WHERE id = :id")
    fun getCoin(id: String): Coin
}