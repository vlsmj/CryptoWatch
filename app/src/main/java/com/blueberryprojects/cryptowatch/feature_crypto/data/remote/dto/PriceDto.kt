package com.blueberryprojects.cryptowatch.feature_crypto.data.remote.dto

import com.blueberryprojects.cryptowatch.common.Constants.CURRENCY
import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName(CURRENCY) val currency: Double,
)