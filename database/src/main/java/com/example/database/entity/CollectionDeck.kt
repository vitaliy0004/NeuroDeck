package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "collectionDeck",
    indices = [Index(value = ["name_collection"])] // Уникальные имена колод
)
data class CollectionDeck(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "name_collection")
    val nameCollection: String
)

@Entity(
    tableName = "infoCards",
    foreignKeys = [
        ForeignKey(
            entity = CollectionDeck::class,
            parentColumns = ["id"],
            childColumns = ["collection_id"],
            onDelete = ForeignKey.CASCADE // Удаляем все карточки при удалении колоды
        )
    ],
    indices = [Index(value = ["question", "collection_id"])] // Уникальные карточки в пределах колоды
)
data class InfoCards(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "collection_id")
    val collectionId: Int, // ForeignKey к CardCollection
    @ColumnInfo(name = "question")
    val question: String,
    @ColumnInfo(name = "answer")
    val answer: String
)