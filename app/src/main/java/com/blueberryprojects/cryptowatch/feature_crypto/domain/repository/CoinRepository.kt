package com.blueberryprojects.cryptowatch.feature_crypto.domain.repository

import com.blueberryprojects.cryptowatch.feature_crypto.data.remote.dto.CoinDataDto
import com.blueberryprojects.cryptowatch.feature_crypto.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getAllCoins(): List<CoinDto>

    suspend fun getCoinById(id: String): CoinDataDto
}