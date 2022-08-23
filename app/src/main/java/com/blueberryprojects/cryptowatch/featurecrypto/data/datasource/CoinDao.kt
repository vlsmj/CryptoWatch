package com.blueberryprojects.cryptowatch.featurecrypto.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin")
    suspend fun getAllCoins(): List<Coin>

    @Query("SELECT * FROM coin WHERE id = :id")
    suspend fun getCoinById(id: String): Coin

    @Insert
    suspend fun insertCoins(coins: List<Coin>)

    @Query("DELETE FROM coin")
    suspend fun deleteAllCoins()
}