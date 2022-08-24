package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components.CoinListItem
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.viewmodel.CoinViewModel

@Composable
fun CoinsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinViewModel = hiltViewModel(),
) {
    val state = viewModel.coinState.value

    Column(modifier = modifier) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.data) { coin ->
                CoinListItem(modifier = Modifier.fillMaxWidth(), coin)
            }
        }
    }
}