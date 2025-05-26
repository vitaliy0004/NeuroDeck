package com.example.database.domain.usecase

import com.example.database.domain.repository.CardCollectionRepository
import javax.inject.Inject

class AddCollectionUseCase @Inject constructor(
    private val repository: CardCollectionRepository
) {
    suspend fun addCollection(name: String){
        repository.addCollection(name)
    }
}