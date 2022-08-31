package com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.blueberryprojects.cryptowatch.common.Constants.CURRENCY_SYMBOL
import com.blueberryprojects.cryptowatch.common.Tags.MARKET_CAP_RANK
import com.blueberryprojects.cryptowatch.common.components.ImageSvg
import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin

@Composable
fun CoinListItem(
    modifier: Modifier = Modifier,
    coin: Coin,
    onItemClick: (id: String) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable {
                onItemClick(coin.id)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            coin.run {
                val name = "$name ($symbol)"
                val price = CURRENCY_SYMBOL + currentPrice
                val low = CURRENCY_SYMBOL + low24h
                val high = CURRENCY_SYMBOL + high24h

                Text(
                    text = marketCapRank.toString(),
                    fontSize = 8.sp,
                    modifier = Modifier
                        .weight(0.25f)
                        .testTag(MARKET_CAP_RANK),
                    color = Color.White
                )
                AsyncImage(
                    model = image,
                    contentDescription = name,
                    modifier = Modifier
                        .size(20.dp)
                        .weight(0.5f)
                )
                Text(
                    text = name,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.8f),
                    color = Color.White
                )
                Text(
                    text = price,
                    fontSize = 8.sp,
                    modifier = Modifier.weight(0.8f),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
                Text(
                    text = low,
                    fontSize = 8.sp,
                    modifier = Modifier.weight(0.8f),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
                Text(
                    text = high,
                    fontSize = 8.sp,
                    modifier = Modifier.weight(0.8f),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
                ImageSvg(
                    modifier = Modifier
                        .size(24.dp)
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    url = sparkline
                )
            }
        }
    }
}