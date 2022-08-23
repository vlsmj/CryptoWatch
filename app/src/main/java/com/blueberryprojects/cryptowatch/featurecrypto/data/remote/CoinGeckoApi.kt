package com.blueberryprojects.cryptowatch.featurecrypto.data.remote

import com.blueberryprojects.cryptowatch.common.Constants.CURRENCY
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDataDto
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    @GET("coins/markets")
    suspend fun getAllCoins(@Query("vs_currency") currency: String = CURRENCY): List<CoinDto>

    @GET("coins/{id}")
    suspend fun getCoinById(@Path("id") id: String): CoinDataDto
}