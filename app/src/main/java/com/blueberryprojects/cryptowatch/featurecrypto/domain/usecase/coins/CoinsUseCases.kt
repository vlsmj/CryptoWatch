package com.blueberryprojects.cryptowatch.featurecrypto.domain.usecase.coins

data class CoinsUseCases(
    val getAllCoinsUseCase: GetAllCoinsUseCase,
    val searchCoinUseCase: SearchCoinUseCase,
    val getCoinDetailsUseCase: GetCoinDetailsUseCase,
)