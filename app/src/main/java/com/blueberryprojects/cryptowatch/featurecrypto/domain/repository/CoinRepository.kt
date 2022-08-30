package com.blueberryprojects.cryptowatch.featurecrypto.domain.repository

import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.CoinData
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getAllCoins(): Flow<Resource<List<Coin>>>

    suspend fun searchCoin(query: String): Flow<Resource<List<Coin>>>

    suspend fun getCoinDetailsById(id: String): Flow<Resource<CoinData>>
}