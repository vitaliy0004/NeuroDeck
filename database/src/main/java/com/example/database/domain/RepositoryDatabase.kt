package com.example.database.domain

import com.example.database.data.Database
import com.example.database.entity.CollectionDeck
import com.example.database.entity.CollectionWithCards
import com.example.database.entity.InfoCards
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class RepositoryDatabase @Inject constructor(
    private val db: Database
) {
    private val cardCollectionDao = db.cardCollectionDao()
    private val infoCardsDao = db.infoCardsDao()

    fun getAllCollections(): Flow<List<CollectionWithCards>> = cardCollectionDao.getAllCollections()

    fun getCollectionName(collectionId: Int): Flow<String> {
        return cardCollectionDao.getCollectionNameById(collectionId)
    }

    suspend fun addCollection(name: String) {
        cardCollectionDao.addCollection(CollectionDeck(nameCollection = name))
    }

    suspend fun deleteCollectionByName(id: Int) {
        cardCollectionDao.deleteCollectionByName(id)
    }

    suspend fun updateCollectionName(collectionDeck: CollectionDeck) {
        cardCollectionDao.updateCollection(collectionDeck)
    }

    fun getCardsForCollection(collectionId: Int): Flow<List<InfoCards>> =
        infoCardsDao.getCardsForCollection(collectionId)

    suspend fun addCard(infoCards: InfoCards) {
        infoCardsDao.addCard(infoCards)
    }

    suspend fun deleteCardByName(id: Int) {
        infoCardsDao.deleteCard(id)
    }

    suspend fun updateCards(infoCards: InfoCards) {
        infoCardsDao.updateCard(infoCards)
    }
}