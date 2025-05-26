package com.example.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.AppNavigation
import com.example.feature_home.home_page.FeatureHomeMainSpace
import javax.inject.Inject

class Navigation @Inject constructor(): AppNavigation {
    @Composable
    override fun AppNavHost() {
        val navController: NavHostController = rememberNavController()

        NavHost(navController, startDestination = FEATURE_HOME_MAIN_SPACE) {
            composable(FEATURE_HOME_MAIN_SPACE) {
                FeatureHomeMainSpace(onNavigateToDeckCard = {
                    navController.navigate(DECK_DISPLAY)
                })
            }

            composable(DECK_DISPLAY){
//                DeckDisplay()
            }
        }

    }

    companion object{
        const val FEATURE_HOME_MAIN_SPACE = "FeatureHomeMainSpace"
        const val DECK_DISPLAY = "DeckDisplay"
    }
}