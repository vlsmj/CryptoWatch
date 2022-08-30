package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.state

import com.blueberryprojects.cryptowatch.common.util.UiText
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.CoinData

data class CoinDetailsState(
    val isLoading: Boolean = false,
    val data: CoinData? = null,
    val errorMessage: UiText? = UiText.DynamicString(""),
)
