package com.example.database.domain.usecase

import com.example.database.domain.repository.CardCollectionRepository
import javax.inject.Inject

class DeleteCollectionByNameUseCase @Inject constructor(
    private val repository: CardCollectionRepository
) {
    suspend fun deleteCollectionByName(id: Int){
        repository.deleteCardByName(id)
    }
}