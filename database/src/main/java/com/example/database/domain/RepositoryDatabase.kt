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

    suspend fun addCard(question: String, answer: String, collectionId: Int) {
        infoCardsDao.addCard(
            InfoCards(
                question = question,
                answer = answer,
                collectionId = collectionId
            )
        )
    }

    suspend fun deleteCardByName(question: String, collectionId: Int) {
        infoCardsDao.deleteCard(question, collectionId)
    }

    suspend fun updateCards(infoCards: InfoCards) {
        infoCardsDao.updateCard(infoCards)
    }
}