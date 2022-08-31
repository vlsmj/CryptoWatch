package com.blueberryprojects.cryptowatch.featurecrypto.presentation

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.test.filters.LargeTest
import com.blueberryprojects.cryptowatch.common.Constants.LIMIT_SIZE
import com.blueberryprojects.cryptowatch.common.Tags.COIN_DETAILS_NAME_SYMBOL
import com.blueberryprojects.cryptowatch.common.Tags.INPUT_SEARCH_COIN
import com.blueberryprojects.cryptowatch.common.Tags.LIST_COINS
import com.blueberryprojects.cryptowatch.di.AppModule
import com.blueberryprojects.cryptowatch.di.RepositoryModule
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen.CoinDetailsScreen
import com.blueberryprojects.cryptowatch.featurecrypto.presentation.coin.screen.CoinsListScreen
import com.blueberryprojects.cryptowatch.ui.theme.CryptoWatchTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@LargeTest
@UninstallModules(AppModule::class, RepositoryModule::class)
class CoinsListScreenEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()

        composeRule.activity.setContent {
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

    @Test
    fun get_notes_limit_size_working() {
        composeRule
            .onNodeWithTag(LIST_COINS)
            .performScrollToIndex(LIMIT_SIZE - 1)

        composeRule
            .onNodeWithText("Coin ${LIMIT_SIZE + 1} (${LIMIT_SIZE + 1})")
            .assertDoesNotExist()
    }

    @Test
    fun navigation_to_coin_details_working() {
        composeRule
            .onNodeWithText("Coin 1 (1)")
            .performClick()

        // Making sure to input empty string in search to fetch all coins first
        // could also work, but prefer this for now.
        composeRule.waitUntil(3000) {
            composeRule
                .onAllNodesWithTag(COIN_DETAILS_NAME_SYMBOL)
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onNodeWithText("Coin 1 (1)")
            .assertIsDisplayed()
    }

    @Test
    fun get_notes_search_working() {
        composeRule
            .onNodeWithTag(INPUT_SEARCH_COIN)
            .performTextInput("Coin 1")

        composeRule
            .onNodeWithText("Coin 1")
            .assertIsDisplayed()
    }
}
























