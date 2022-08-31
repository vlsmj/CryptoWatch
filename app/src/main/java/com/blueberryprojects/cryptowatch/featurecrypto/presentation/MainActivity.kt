package com.blueberryprojects.cryptowatch.featurecrypto.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen.CoinDetailsScreen
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen.CoinsListScreen
import com.blueberryprojects.cryptowatch.ui.theme.CryptoWatchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoWatchTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinsListScreen.route
                    ) {
                        composable(
                            route = Screen.CoinsListScreen.route
                        ) {
                            CoinsListScreen(
                                navController,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 4.dp)
                            )
                        }
                        composable(
                            route = Screen.CoinDetailsScreen.route + "?id={id}",
                            arguments = listOf(
                                navArgument(
                                    name = "id"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            CoinDetailsScreen(
                                modifier = Modifier
                                    .fillMaxSize(),
                                it.arguments?.getString("id") ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}