package com.blueberryprojects.cryptowatch.feature_crypto.domain.repository

interface CoinRepository {

    fun getAllCoins()

    fun getCoinById(id: String)
}