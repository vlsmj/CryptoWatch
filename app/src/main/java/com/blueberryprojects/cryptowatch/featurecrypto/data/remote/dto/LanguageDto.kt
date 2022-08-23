package com.blueberryprojects.cryptowatch.featurecrypto.data.remote.dto

import com.blueberryprojects.cryptowatch.common.Constants.LANGUAGE
import com.google.gson.annotations.SerializedName

data class LanguageDto(
    @SerializedName(LANGUAGE) val language: String,
)