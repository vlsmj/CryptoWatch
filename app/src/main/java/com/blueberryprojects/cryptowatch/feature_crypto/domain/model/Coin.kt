package com.blueberryprojects.cryptowatch.feature_crypto.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coin(
    @PrimaryKey val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val marketCapRank: Int,
    val low24h: Double,
    val high24h: Double,
    val sparkline: String,
)