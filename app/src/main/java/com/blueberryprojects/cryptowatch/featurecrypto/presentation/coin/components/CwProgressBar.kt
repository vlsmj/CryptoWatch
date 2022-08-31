package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CwProgressBar(
    modifier: Modifier,
    strokeWidth: Dp = 2.dp,
) {

    CircularProgressIndicator(
        modifier = modifier,
        strokeWidth = strokeWidth
    )
}