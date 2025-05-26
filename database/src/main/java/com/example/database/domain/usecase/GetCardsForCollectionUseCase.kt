package com.example.database.domain.usecase

import com.example.database.domain.entity.InfoCards
import com.example.database.domain.repository.CardCollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCardsForCollectionUseCase @Inject constructor(
    private val repository: CardCollectionRepository
) {
    fun getCardsForCollection(collectionId: Int): Flow<List<InfoCards>>{
        return repository.getCardsForCollection(collectionId)
    }
}