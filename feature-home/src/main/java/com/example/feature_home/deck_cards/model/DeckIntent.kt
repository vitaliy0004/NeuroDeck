package com.example.feature_home.deck_cards.model

sealed class DeckIntent {
    data class LoadCollection(val collectionId: Int) : DeckIntent()
    data class ToggleCard(val card: Card) : DeckIntent()
}