package com.example.database.domain.usecase

import com.example.database.domain.entity.CollectionDeck
import com.example.database.domain.repository.CardCollectionRepository
import javax.inject.Inject

class UpdateCollectionNameUseCase @Inject constructor(
    private val repository: CardCollectionRepository
) {
    suspend fun updateCollectionName(collectionDeck: CollectionDeck){
        repository.updateCollectionName(collectionDeck)
    }
}