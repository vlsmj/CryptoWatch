package com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto

import com.blueberryprojects.cryptowatch.common.Constants.CURRENCY
import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName(CURRENCY) val currency: Double,
)