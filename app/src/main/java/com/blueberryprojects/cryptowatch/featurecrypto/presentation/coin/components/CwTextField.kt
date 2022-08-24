package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components

import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun CwTextField(
    modifier: Modifier,
    onValueChange: (input: String) -> Unit,
) {
    var text by remember {
        mutableStateOf("")
    }

    TextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = it
            onValueChange(text)
        }
    )
}