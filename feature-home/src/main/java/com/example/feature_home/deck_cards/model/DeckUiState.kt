package com.example.feature_home.deck_cards.model

data class DeckUiState(
    val collectionName: String = "",
    val cards: List<Card> = emptyList()
)