    package com.example.feature_home.deck_cards.model

    data class Card(
        val id: Int,
        val question: String,
        val answer: String,
        val isExpanded: Boolean = false // состояние раскрытия
    )
