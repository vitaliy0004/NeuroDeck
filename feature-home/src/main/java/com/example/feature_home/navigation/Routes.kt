package com.example.feature_home.navigation

sealed class routes(val route: String) {
    object Home: routes("decks")
    object Cards: routes("cards")
    object Test: routes("test")
}