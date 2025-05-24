package com.example.feature_home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature_home.home_page.FeatureHomeMainSpace

@Composable
fun navigation(): NavController{
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route){
        composable(Routes.Home.route){ FeatureHomeMainSpace() }
        composable(Routes.Cards.route){}
        composable(Routes.Test.route){}
    }
    return navController
}