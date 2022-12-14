package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.blueberryprojects.cryptowatch.common.Tags.INPUT_SEARCH_COIN
import com.blueberryprojects.cryptowatch.common.Tags.LIST_COINS
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.Screen
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components.CoinListItem
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components.CwProgressBar
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components.CwTextField
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.viewmodel.CoinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CoinsListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CoinViewModel = hiltViewModel(),
) {
    val state = viewModel.coinState.value

    var closeKeyboardState by remember {
        mutableStateOf(false)
    }

    if (closeKeyboardState) {
        LocalSoftwareKeyboardController.current?.hide()
    }

    Box {
        if (state.isLoading) {
            CwProgressBar(modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center)
            )
        }

        Column(modifier = modifier) {
            CwTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(32.dp))
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.DarkGray)
                    .testTag(INPUT_SEARCH_COIN),
                hint = "Search coin",
                query = state.query
            ) { input, closeKeyboard ->
                viewModel.coinState.value = viewModel.coinState.value.copy(query = input)

                if (input.isBlank()) {
                    viewModel.getAllCoins()
                } else {
                    viewModel.searchCoin(input)
                }

                closeKeyboardState = closeKeyboard
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Rank",
                    fontSize = 8.sp,
                    modifier = Modifier.weight(0.4f),
                    color = Color.White
                )
                Text(
                    text = "Coin",
                    fontSize = 8.sp,
                    modifier = Modifier.weight(2.27f),
                    color = Color.White
                )
                Text(
                    text = "Price",
                    fontSize = 8.sp,
                    modifier = Modifier.weight(0.6f),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
                Text(
                    text = "Low 24h",
                    fontSize = 8.sp,
                    modifier = Modifier.weight(0.77f),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
                Text(
                    text = "High 24h",
                    fontSize = 8.sp,
                    modifier = Modifier.weight(0.77f),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
                Text(
                    text = "Last 7 Days",
                    fontSize = 8.sp,
                    modifier = Modifier.weight(0.97f),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }

            LazyColumn(
                modifier = modifier
                    .testTag(LIST_COINS),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.data) { coin ->
                    CoinListItem(modifier = Modifier.fillMaxWidth(), coin) { id ->
                        navController.navigate(Screen.CoinDetailsScreen.route + "?id=${id}")
                    }
                }
            }
        }
    }
}