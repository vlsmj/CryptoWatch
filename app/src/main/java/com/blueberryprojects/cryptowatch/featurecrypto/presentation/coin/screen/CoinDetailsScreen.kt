package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueberryprojects.cryptowatch.common.Constants
import com.blueberryprojects.cryptowatch.common.Tags.COIN_DETAILS_NAME_SYMBOL
import com.blueberryprojects.cryptowatch.common.components.ImageSvg
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components.CwProgressBar
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.viewmodel.CoinViewModel

@Composable
fun CoinDetailsScreen(
    modifier: Modifier,
    id: String,
    viewModel: CoinViewModel = hiltViewModel(),
) {
    val coinDetailsState = viewModel.coinDetailsState.value

    val scrollState = rememberScrollState()

    var openedLink by remember {
        mutableStateOf("")
    }

    viewModel.getCoinDetails(id)

    if (openedLink.isNotEmpty()) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(openedLink)
            }
        }, update = {
            it.loadUrl(openedLink)
        })
    } else {
        Box {
            if (coinDetailsState.isLoading) {
                CwProgressBar(modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center)
                )
            }

            coinDetailsState.data?.let {
                Column(
                    modifier = modifier
                        .padding(16.dp)
                        .verticalScroll(scrollState)
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ImageSvg(
                            url = it.image,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.2f)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(
                                modifier = Modifier.testTag(COIN_DETAILS_NAME_SYMBOL),
                                fontSize = 12.sp,
                                text = "${it.name} (${it.symbol})"
                            )
                            Text(
                                fontSize = 12.sp,
                                text = "Market Cap: ${Constants.CURRENCY_SYMBOL}${it.marketCap}"
                            )
                            Text(
                                fontSize = 12.sp,
                                text = buildAnnotatedString {
                                    val urlStyle = SpanStyle(
                                        color = Color(0xFF3895D3),
                                    )
                                    pushStyle(urlStyle)
                                    append(it.link ?: "")
                                },
                                modifier = Modifier.clickable {
                                    openedLink = it.link ?: ""
                                }
                            )
                        }
                    }
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    Text(
                        fontSize = 12.sp,
                        text = it.description
                    )
                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 12.sp,
                        text = "Current Price: ${Constants.CURRENCY_SYMBOL}${it.currentPrice}",
                        textAlign = TextAlign.Center,
                    )
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            fontSize = 12.sp,
                            text = "% ${it.priceChangePercentage24h} (24h)"
                        )
                        Text(
                            fontSize = 12.sp,
                            text = "% ${it.priceChangePercentage7d} (7d)"
                        )
                        Text(
                            fontSize = 12.sp,
                            text = "% ${it.priceChangePercentage14d} (14d)"
                        )
                    }
                }
            }
        }
    }
}