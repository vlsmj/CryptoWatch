package com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto

import com.blueberryprojects.cryptowatch.featurecrypto.domain.model.CoinData
import com.google.gson.annotations.SerializedName

data class CoinDataDto(
    val id: String,
    val symbol: String,
    val name: String,
    @SerializedName("image") val imageData: ImageDataDto,
    @SerializedName("market_data") val marketData: MarketDataDto,
    val description: LanguageDto,
    val links: HomepageDto,
)

fun CoinDataDto.toCoinData(): CoinData {
    return CoinData(
        id = this.id,
        symbol = this.symbol,
        name = this.name,
        image = this.imageData.small,
        description = this.description.language,
        currentPrice = this.marketData.currentPrice.currency,
        priceChangePercentage24h = this.marketData.priceChangePercentage24h,
        priceChangePercentage7d = this.marketData.priceChangePercentage7d,
        priceChangePercentage14d = this.marketData.priceChangePercentage7d,
        marketCap = this.marketData.marketCap.currency,
        link = this.links.homepage[0]
    )
}