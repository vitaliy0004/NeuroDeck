package com.example.database.data.entity

import androidx.room.Embedded
import androidx.room.Relation


data class CollectionWithCardsEntity(
    @Embedded
    val collection: CollectionDeckEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "collection_id"
    )
    val cards: List<InfoCardsEntity>?
)