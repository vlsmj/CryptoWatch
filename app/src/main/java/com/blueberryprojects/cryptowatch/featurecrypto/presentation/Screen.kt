package com.blueberryprojects.cryptowatch.featurecrypto.presentation

sealed class Screen(val route: String) {
    object CoinsListScreen : Screen("coins_list_screen")
    object CoinDetailsScreen : Screen("coin_details_screen")
}
