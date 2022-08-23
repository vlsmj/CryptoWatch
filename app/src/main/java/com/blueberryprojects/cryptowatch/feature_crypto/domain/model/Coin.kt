package com.blueberryprojects.cryptowatch.feature_crypto.domain.model

data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val marketCapRank: Int,
    val low24h: Double,
    val high24h: Double,
    val sparkline: String,
)