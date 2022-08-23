package com.blueberryprojects.cryptowatch.featurecrypto.presentation

sealed class Screen(val route: String) {
    object CoinsListScreen: Screen("coins_list_screen")
}
