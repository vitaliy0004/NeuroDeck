package com.example.database.domain.entity

data class CollectionDeck(
    val id: Int?,
    val nameCollection: String
)
data class InfoCards(
    val id: Int,
    val collectionId: Int,
    val question: String,
    val answer: String
)