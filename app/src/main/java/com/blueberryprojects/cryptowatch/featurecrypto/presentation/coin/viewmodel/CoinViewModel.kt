package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueberryprojects.cryptowatch.common.Constants.DELAY_CANCEL
import com.blueberryprojects.cryptowatch.common.util.Resource
import com.blueberryprojects.cryptowatch.featurecrypto.domain.usecase.coins.CoinsUseCases
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.state.CoinDetailsState
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.state.CoinState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinsUseCases: CoinsUseCases,
) : ViewModel() {

    private var job: Job? = null

    var coinState = mutableStateOf(CoinState())
        private set

    var coinDetailsState = mutableStateOf(CoinDetailsState())
        private set

    init {
        getAllCoins()
    }

    fun getAllCoins() {
        job?.let {
            if (it.isActive) it.cancel()
        }

        coinsUseCases.getAllCoinsUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    coinState.value = coinState.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    coinState.value = coinState.value.copy(data = it.data ?: mutableListOf(), isLoading = false)
                }
                is Resource.Error -> {
                    coinState.value = coinState.value.copy(errorMessage = it.errorMessage, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchCoin(query: String) {
        job?.let {
            if (it.isActive) it.cancel()
        }

        job = viewModelScope.launch {

            delay(DELAY_CANCEL)
            coinsUseCases.searchCoinUseCase(query).collect {
                when (it) {
                    is Resource.Loading -> {
                        coinState.value = coinState.value.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        coinState.value = coinState.value.copy(data = it.data ?: mutableListOf(), isLoading = false)
                    }
                    is Resource.Error -> {
                        coinState.value = coinState.value.copy(errorMessage = it.errorMessage, isLoading = false)
                    }
                }
            }
        }
    }

    fun getCoinDetails(id: String) {
        coinsUseCases.getCoinDetailsUseCase(id).onEach {
            when (it) {
                is Resource.Loading -> {
                    coinDetailsState.value = coinDetailsState.value.copy(isLoading = true)
                }
                is Resource.Success -> {
                    coinDetailsState.value = coinDetailsState.value.copy(data = it.data, isLoading = false)
                }
                is Resource.Error -> {
                    coinDetailsState.value = coinDetailsState.value.copy(errorMessage = it.errorMessage, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}