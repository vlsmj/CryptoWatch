package com.blueberryprojects.cryptowatch.featurecrypto.domain.repository

import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDataDto
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinsDto
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getAllCoins(): Flow<Resource<List<Coin>>>

    suspend fun searchCoin(query: String): Flow<Resource<List<Coin>>>

    suspend fun getCoinById(id: String): CoinDataDto
}