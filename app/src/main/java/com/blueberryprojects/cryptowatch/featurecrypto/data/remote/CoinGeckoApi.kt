package com.blueberryprojects.cryptowatch.featurecrypto.data.remote

import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDataDto
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinGeckoApi {

    @GET("v3/coins/markets")
    suspend fun getAllCoins(): List<CoinDto>

    @GET("v3/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): CoinDataDto
}