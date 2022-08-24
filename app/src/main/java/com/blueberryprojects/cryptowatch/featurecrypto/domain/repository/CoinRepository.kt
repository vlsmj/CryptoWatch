package com.blueberryprojects.cryptowatch.featurecrypto.domain.repository

import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDataDto
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDto
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinsDto

interface CoinRepository {

    suspend fun getAllCoins(): List<CoinDto>

    suspend fun searchCoin(query: String): CoinsDto

    suspend fun getCoinById(id: String): CoinDataDto
}