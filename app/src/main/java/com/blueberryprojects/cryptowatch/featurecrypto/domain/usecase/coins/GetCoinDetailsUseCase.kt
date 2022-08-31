package com.blueberryprojects.cryptowatch.featurecrypto.domain.usecase.coins

import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.CoinData
import com.blueberryprojects.cryptowatch.featurecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
) {
    operator fun invoke(id: String): Flow<Resource<CoinData>> = flow {
        coinRepository.getCoinDetailsById(id).collect {
            emit(it)
        }
    }
}