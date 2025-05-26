package com.example.database.domain.usecase

import com.example.database.domain.repository.CardCollectionRepository
import javax.inject.Inject

class DeleteCardByNameUseCase @Inject constructor(
    private val repository: CardCollectionRepository
) {
    suspend fun deleteCardByName(id: Int){
        repository.deleteCardByName(id)
    }
}