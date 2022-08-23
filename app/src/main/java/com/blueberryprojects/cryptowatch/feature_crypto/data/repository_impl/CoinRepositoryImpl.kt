package com.blueberryprojects.cryptowatch.feature_crypto.data.repository_impl

import com.blueberryprojects.cryptowatch.feature_crypto.data.remote.CoinGeckoApi
import com.blueberryprojects.cryptowatch.feature_crypto.data.remote.dto.CoinDataDto
import com.blueberryprojects.cryptowatch.feature_crypto.data.remote.dto.CoinDto
import com.blueberryprojects.cryptowatch.feature_crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinGeckoApi: CoinGeckoApi,
) : CoinRepository {

    override suspend fun getAllCoins(): List<CoinDto> {
        return coinGeckoApi.getAllCoins()
    }

    override suspend fun getCoinById(id: String): CoinDataDto {
        return coinGeckoApi.getCoinById(id)
    }
}