package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.state

import com.blueberryprojects.cryptowatch.common.util.UiText
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin

data class CoinState(
    val isLoading: Boolean = false,
    val data: List<Coin> = mutableListOf(),
    val errorMessage: UiText? = UiText.DynamicString(""),
)
