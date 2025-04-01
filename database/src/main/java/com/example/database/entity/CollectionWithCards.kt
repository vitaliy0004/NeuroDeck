package com.example.database.entity

import androidx.room.Embedded
import androidx.room.Relation


data class CollectionWithCards(
    @Embedded
    val collection: CollectionDeck,
    @Relation(
        parentColumn = "id",
        entityColumn = "collection_id"
    )
    val cards: List<InfoCards>?
)