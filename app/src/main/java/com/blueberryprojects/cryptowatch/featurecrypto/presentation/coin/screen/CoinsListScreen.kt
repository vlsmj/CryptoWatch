package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.viewmodel.CoinViewModel

@Composable
fun CoinsListScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinViewModel = hiltViewModel(),
) {
    val state = viewModel.coinState.value

    Column(modifier = modifier) {
        LazyColumn(modifier = modifier) {
            items(state.data) { coin ->
                Text(
                    text = coin.name,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black
                )
            }
        }
    }
}