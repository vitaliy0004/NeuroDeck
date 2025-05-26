package com.example.feature_home.deck_cards.model

sealed class DeckEffect {
    data class ShowError(val message: String) : DeckEffect()
}