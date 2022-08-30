package com.blueberryprojects.cryptowatch.featurecrypto.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.CoinData

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin")
    suspend fun getAllCoins(): List<Coin>

    @Query("SELECT * FROM coindata")
    suspend fun getCoinDetails(): CoinData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinDetails(coin: CoinData)

    @Query("DELETE FROM coindata")
    suspend fun deleteCoinDetails()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<Coin>)

    @Query("DELETE FROM coin")
    suspend fun deleteAllCoins()
}