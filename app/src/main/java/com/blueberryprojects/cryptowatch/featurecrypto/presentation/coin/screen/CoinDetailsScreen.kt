package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueberryprojects.cryptowatch.common.components.ImageSvg
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.viewmodel.CoinViewModel

@Composable
fun CoinDetailsScreen(
    modifier: Modifier,
    viewModel: CoinViewModel = hiltViewModel(),
) {
    val coinDetailsState = viewModel.coinDetailsState.value

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageSvg(url = "")
        }
    }
}