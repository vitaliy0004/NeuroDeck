package com.example.database.domain.usecase

import com.example.database.domain.entity.InfoCards
import com.example.database.domain.repository.CardCollectionRepository
import javax.inject.Inject

class AddCardUseCase @Inject constructor(
    private val repository: CardCollectionRepository
) {
    suspend fun addCard(infoCards: InfoCards){
        repository.addCard(infoCards)
    }
}