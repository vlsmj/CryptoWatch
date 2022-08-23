package com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto

import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price") val currentPrice: Double,
    @SerializedName("market_cap_rank") val marketCapRank: Int,
    @SerializedName("low_24h") val low24h: Double,
    @SerializedName("high_24h") val high24h: Double,
)

fun CoinDto.toCoin(): Coin {
    val id = name.substringAfter("/images/").run {
        this.substringBefore("/")
    }

    val sparklineUrl = "https://www.coingecko.com/coins/$id/sparkline"

    return Coin(
        id = this.id,
        symbol = this.symbol,
        name = this.name,
        image = this.image,
        currentPrice = this.currentPrice,
        marketCapRank = this.marketCapRank,
        low24h = this.low24h,
        high24h = this.high24h,
        sparkline = sparklineUrl
    )
}