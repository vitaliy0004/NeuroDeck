package com.example.core

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.example.database.domain.RepositoryDatabase
import com.example.database.entity.CollectionDeck
import com.example.database.entity.InfoCards
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    application: Application,
    private val repositoryDatabase: RepositoryDatabase
) : AndroidViewModel(application = application) {

    val allCollection = repositoryDatabase.getAllCollections()

    suspend fun addCollection(name: String) {
        repositoryDatabase.addCollection(name)
        checkForDuplicateCollections(name)
    }

    suspend fun deleteCollection(id: Int) {
        repositoryDatabase.deleteCollectionByName(id)
    }

    fun getCardsForCollection(collectionId: Int): Flow<List<InfoCards>> {
        return repositoryDatabase.getCardsForCollection(collectionId)
    }

    suspend fun updateCollectionName(collectionDeck: CollectionDeck) {
        repositoryDatabase.updateCollectionName(collectionDeck)
        checkForDuplicateCollections(collectionDeck.nameCollection)
    }

    suspend fun addCard(infoCards: InfoCards) {
        repositoryDatabase.addCard(infoCards)
        checkForDuplicateCards(infoCards.question, infoCards.answer, infoCards.collectionId)
    }

    suspend fun deleteCard(id: Int) {
        repositoryDatabase.deleteCardByName(id)
    }

    suspend fun updateCardByName(infoCards: InfoCards) {
        repositoryDatabase.updateCards(infoCards)
        checkForDuplicateCards(infoCards.question, infoCards.answer, infoCards.collectionId)
    }

    private suspend fun checkForDuplicateCards(
        question: String,
        answer: String,
        collectionId: Int
    ) {
        var checkForDuplicateQuestion: Int = -1
        var checkForDuplicateAnswer: Int = -1
        val listInfoCards = getCardsForCollection(collectionId).first()
        listInfoCards.forEach { infoCards ->
            if (question == infoCards.question) checkForDuplicateQuestion++
            if (answer == infoCards.answer) checkForDuplicateAnswer++
        }
        if (checkForDuplicateQuestion != 0 && checkForDuplicateAnswer != 0)
            Toast.makeText(
                getApplication(),
                "лицевая  сторона карточки с надписью $question дублируется  $checkForDuplicateQuestion раз\nобратная сторона карточки с надписью $answer дублируется  $checkForDuplicateAnswer раз",
                Toast.LENGTH_SHORT
            ).show()
        else if (checkForDuplicateQuestion != 0 && checkForDuplicateAnswer == 0)
            Toast.makeText(
                getApplication(),
                "лицевая  сторона карточки с надписью $question дублируется  $checkForDuplicateQuestion раз",
                Toast.LENGTH_SHORT
            ).show()
        else if (checkForDuplicateQuestion == 0 && checkForDuplicateAnswer != 0)
            Toast.makeText(
                getApplication(),
                "обратная сторона карточки с надписью $answer дублируется  $checkForDuplicateAnswer раз",
                Toast.LENGTH_SHORT
            ).show()
    }

    private suspend fun checkForDuplicateCollections(nameCollection: String) {
        var checkForDuplicate: Int = -1
        val listCollectionWithCards = allCollection.first()
            listCollectionWithCards.forEach { collectionWithCards ->
            if (nameCollection == collectionWithCards.collection.nameCollection)
                checkForDuplicate++
        }
        if (checkForDuplicate != 0) Toast.makeText(
            getApplication(),
            "коллекция $nameCollection у вас повторяется $checkForDuplicate раз",
            Toast.LENGTH_SHORT
        ).show()
    }
}