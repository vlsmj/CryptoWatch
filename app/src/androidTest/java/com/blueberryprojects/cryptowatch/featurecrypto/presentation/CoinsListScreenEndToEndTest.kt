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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.LargeTest
import com.blueberryprojects.cryptowatch.common.Constants.LIMIT_SIZE
import com.blueberryprojects.cryptowatch.common.Tags.INPUT_SEARCH_COIN
import com.blueberryprojects.cryptowatch.common.Tags.LIST_COINS
import com.blueberryprojects.cryptowatch.common.Tags.MARKET_CAP_RANK
import com.blueberryprojects.cryptowatch.di.AppModule
import com.blueberryprojects.cryptowatch.di.RepositoryModule
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

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
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
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    @Test
    fun get_notes_limit_size_working() {
        composeRule.waitUntil {
            composeRule
                .onAllNodes(hasTestTag(MARKET_CAP_RANK))
                .fetchSemanticsNodes().isNotEmpty()
        }

        composeRule
            .onNodeWithTag(LIST_COINS)
            .performScrollToIndex(LIMIT_SIZE - 1)

        composeRule
            .onNodeWithText("Coin ${LIMIT_SIZE + 1} (${LIMIT_SIZE + 1})")
            .assertDoesNotExist()
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
























