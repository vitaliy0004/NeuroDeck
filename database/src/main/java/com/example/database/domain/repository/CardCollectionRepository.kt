package com.example.database.domain.repository


import com.example.database.domain.entity.CollectionDeck
import com.example.database.domain.entity.CollectionWithCards
import com.example.database.domain.entity.InfoCards
import kotlinx.coroutines.flow.Flow

interface CardCollectionRepository {
    fun getAllCollections(): Flow<List<CollectionWithCards>>

    suspend fun addCollection(name: String)

    suspend fun deleteCollectionByName(id: Int)

    suspend fun updateCollectionName(collectionDeck: CollectionDeck)

    fun getCardsForCollection(collectionId: Int): Flow<List<InfoCards>>

    suspend fun addCard(infoCards: InfoCards)

    suspend fun deleteCardByName(id: Int)

    suspend fun updateCards(infoCards: InfoCards)
}