package com.example.database.domain.usecase

import com.example.database.domain.entity.CollectionDeck
import com.example.database.domain.entity.InfoCards
import com.example.database.domain.repository.CardCollectionRepository
import javax.inject.Inject

class UpdateCardsUseCase @Inject constructor(
    private val repository: CardCollectionRepository
) {
    suspend fun updateCards(infoCards: InfoCards){
        repository.updateCards(infoCards)
    }
}