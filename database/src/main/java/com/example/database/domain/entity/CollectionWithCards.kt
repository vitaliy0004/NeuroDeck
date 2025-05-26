package com.example.database.domain.entity

data class CollectionWithCards(
    val collection: CollectionDeck,
    val cards: List<InfoCards>?
)