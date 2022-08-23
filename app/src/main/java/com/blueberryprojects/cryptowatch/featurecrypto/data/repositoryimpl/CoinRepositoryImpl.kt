package com.blueberryprojects.cryptowatch.featurecrypto.data.repositoryimpl

import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.CoinGeckoApi
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDataDto
import com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto.CoinDto
import com.blueberryprojects.cryptowatch.featurecrypto.domain.repository.CoinRepository
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