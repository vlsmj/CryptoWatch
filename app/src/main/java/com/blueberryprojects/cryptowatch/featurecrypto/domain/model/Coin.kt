package com.blueberryprojects.cryptowatch.featurecrypto.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coin(
    @PrimaryKey val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double? = 0.0,
    val marketCapRank: Int,
    val low24h: Double? = 0.0,
    val high24h: Double? = 0.0,
    val sparkline: String? = "",
)