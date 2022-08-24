package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.featurecrypto.domain.usecase.coins.CoinsUseCases
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.state.CoinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinsUseCases: CoinsUseCases,
) : ViewModel() {
    var coinState = mutableStateOf(CoinState())
        private set

    init {
        getAllCoins()
    }

    private fun getAllCoins() {
        coinsUseCases.getAllCoinsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    coinState.value = CoinState(isLoading = true)
                }
                is Resource.Success -> {
                    coinState.value = CoinState(data = it.data ?: mutableListOf())
                }
                is Resource.Error -> {
                    coinState.value = CoinState(errorMessage = it.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchCoin(query: String) {
        coinsUseCases.searchCoinUseCase(query).onEach {
            when (it) {
                is Resource.Loading -> {
                    coinState.value = CoinState(isLoading = true)
                }
                is Resource.Success -> {
                    coinState.value = CoinState(data = it.data ?: mutableListOf())
                }
                is Resource.Error -> {
                    coinState.value = CoinState(errorMessage = it.errorMessage)
                }
            }
        }.launchIn(viewModelScope)
    }
}