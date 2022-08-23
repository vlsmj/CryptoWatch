package com.blueberryprojects.cryptowatch.feature_crypto.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MarketDataDto(
    @SerializedName("current_price") val currentPrice: PriceDto,
    @SerializedName("price_change_percentage_24h") val priceChangePercentage24h: Double,
    @SerializedName("price_change_percentage_7d") val priceChangePercentage7d: Double,
    @SerializedName("price_change_percentage_14d") val priceChangePercentage14d: Double,
    @SerializedName("market_cap") val marketCap: PriceDto,
)