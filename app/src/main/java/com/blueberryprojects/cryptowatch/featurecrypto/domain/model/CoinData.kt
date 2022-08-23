package com.blueberryprojects.cryptowatch.featurecrypto.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinData(
    @PrimaryKey val id: String,
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