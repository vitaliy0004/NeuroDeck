package com.example.database.data

import com.example.database.domain.repository.CardCollectionRepository
import com.example.database.data.entity.CollectionDeckEntity
import com.example.database.data.entity.CollectionWithCardsEntity
import com.example.database.data.entity.InfoCardsEntity
import com.example.database.domain.entity.CollectionDeck
import com.example.database.domain.entity.CollectionWithCards
import com.example.database.domain.entity.InfoCards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RepositoryDatabase @Inject constructor(
    db: Database
) : CardCollectionRepository {
    private val cardCollectionDao = db.cardCollectionDao()
    private val infoCardsDao = db.infoCardsDao()

    override fun getAllCollections(): Flow<List<CollectionWithCards>> {
        val collectionWithCardsEntity = cardCollectionDao.getAllCollections()
        return collectionWithCardsDataToCollectionWithCardsDomain(collectionWithCardsEntity)
    }


    override suspend fun addCollection(name: String) {
        cardCollectionDao.addCollection(CollectionDeckEntity(nameCollection = name))
    }

    override suspend fun deleteCollectionByName(id: Int) {
        cardCollectionDao.deleteCollectionByName(id)
    }

    override suspend fun updateCollectionName(collectionDeck: CollectionDeck) {
        val collectionDeckEntity = collectionDeckDomainToCollectionDeckDomainData(collectionDeck)
        cardCollectionDao.updateCollection(collectionDeckEntity)
    }

    override fun getCardsForCollection(collectionId: Int): Flow<List<InfoCards>> {
        val infoCardsEntity = infoCardsDao.getCardsForCollection(collectionId)
        return infoCardsDataToInfoCardsDomainFlow(infoCardsEntity)
    }


    override suspend fun addCard(infoCards: InfoCards) {
        val infoCardsEntity = infoCardsDomainToInfoCardsData(infoCards)
        infoCardsDao.addCard(infoCardsEntity)
    }

    override suspend fun deleteCardByName(id: Int) {
        infoCardsDao.deleteCard(id)
    }

    override suspend fun updateCards(infoCards: InfoCards) {
        val infoCardsEntity = infoCardsDomainToInfoCardsData(infoCards)
        infoCardsDao.updateCard(infoCardsEntity)
    }

    companion object {
        private fun collectionDeckDomainToCollectionDeckDomainData(collectionDeck: CollectionDeck): CollectionDeckEntity {
            return CollectionDeckEntity(
                id = collectionDeck.id,
                nameCollection = collectionDeck.nameCollection
            )

        }

        private fun infoCardsDataToInfoCardsDomainFlow(infoCardsEntity: Flow<List<InfoCardsEntity>>): Flow<List<InfoCards>> {
            return infoCardsEntity.map { list ->
                list.map { entity ->
                    InfoCards(
                        id = entity.id,
                        collectionId = entity.collectionId,
                        question = entity.question,
                        answer = entity.answer
                    )
                }
            }
        }

        private fun infoCardsDomainToInfoCardsData(infoCards: InfoCards): InfoCardsEntity {
            return InfoCardsEntity(
                id = infoCards.id,
                collectionId = infoCards.collectionId,
                question = infoCards.question,
                answer = infoCards.answer
            )
        }

        private fun collectionWithCardsDataToCollectionWithCardsDomain(
            collectionWithCardsEntityFlow: Flow<List<CollectionWithCardsEntity>>
        ): Flow<List<CollectionWithCards>> {
            return collectionWithCardsEntityFlow.map { list ->
                list.map { collectionWithCardsEntity ->
                    CollectionWithCards(
                        collection = CollectionDeck(
                            id = collectionWithCardsEntity.collection.id,
                            nameCollection = collectionWithCardsEntity.collection.nameCollection
                        ),
                        cards = collectionWithCardsEntity.cards?.map { infoCardsEntity ->
                            InfoCards(
                                id = infoCardsEntity.id,
                                collectionId = infoCardsEntity.collectionId,
                                question = infoCardsEntity.question,
                                answer = infoCardsEntity.answer,
                            )
                        }
                    )
                }
            }
        }
    }
}