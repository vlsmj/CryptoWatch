package com.blueberryprojects.cryptowatch.feature_crypto.domain.model

data class CoinData(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val description: String,
    val currentPrice: Double,
    val priceChangePercentage24h: Double,
    val priceChangePercentage7d: Double,
    val priceChangePercentage14d: Double,
    val marketCap: Double,
    val link: String,
)