package com.example.feature_home.navigation

sealed class Routes(val route: String) {
    object Home: Routes("decks")
    object Cards: Routes("cards")
    object Test: Routes("test")
}