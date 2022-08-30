package com.blueberryprojects.cryptowatch.featurecrypto.domain.usecase.coins

import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin
import com.blueberryprojects.cryptowatch.featurecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        coinRepository.getAllCoins().collect {
            emit(it)
        }
    }
}