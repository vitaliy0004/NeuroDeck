package com.example.database.domain.usecase

import com.example.database.domain.entity.CollectionWithCards
import com.example.database.domain.repository.CardCollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCollectionsUseCase @Inject constructor(
    private val repository: CardCollectionRepository
) {
    fun getAllCollections(): Flow<List<CollectionWithCards>> {
        return repository.getAllCollections()
    }
}