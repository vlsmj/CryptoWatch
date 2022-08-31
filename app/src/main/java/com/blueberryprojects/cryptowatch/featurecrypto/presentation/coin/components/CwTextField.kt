package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CwTextField(
    modifier: Modifier,
    hint: String,
    query: String,
    onValueChange: (input: String, closeKeyboard: Boolean) -> Unit,
) {
    var textState by remember {
        mutableStateOf(query)
    }

    BasicTextField(
        modifier = modifier,
        value = textState,
        textStyle = TextStyle(
            fontSize = 12.sp,
            color = Color.White
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            autoCorrect = false
        ),
        onValueChange = {
            textState = it
            onValueChange(textState, false)
        },
        enabled = true,
        singleLine = true,
        cursorBrush = SolidColor(Color.White),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.9f)
                ) {
                    if (textState.isEmpty()) {
                        Text(
                            modifier = Modifier.alpha(0.5f),
                            text = hint,
                            color = Color.White,
                            fontSize = 12.sp,
                        )
                    }
                    innerTextField()
                }
                if (textState.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        modifier = Modifier
                            .weight(0.1f)
                            .size(16.dp)
                            .clickable {
                                textState = ""
                                onValueChange(textState, true)
                            }
                    )
                }
            }
        }
    )
}