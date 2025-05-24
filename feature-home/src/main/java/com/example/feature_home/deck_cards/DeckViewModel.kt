package com.example.feature_home.deck_cards

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.domain.RepositoryDatabase
import com.example.database.entity.InfoCards
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DeckViewModel @Inject constructor(
    private val repository: RepositoryDatabase
) : ViewModel() {

    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> get() = _cards

    fun loadCards(collectionId: Int) {
        viewModelScope.launch {
            repository.getCardsForCollection(collectionId)
                .collectLatest { dbCards ->
                    _cards.value = dbCards.map {
                        Card(
                            id = it.id,
                            question = it.question,
                            answer = it.answer,
                            isExpanded = false
                        )
                    }
                }
        }
    }

    fun toggleCardExpansion(card: Card) {
        viewModelScope.launch {
            val updatedCard = card.copy(isExpanded = !card.isExpanded)
            _cards.value = _cards.value.map {
                if (it.id == card.id) updatedCard else it
            }
            // Если нужно сохранять состояние в БД:
            // repository.updateCard(InfoCards(card.id, card.question, card.answer))
        }
    }


    private val _collectionName = MutableStateFlow("")
    val collectionName: StateFlow<String> = _collectionName

    fun loadCollectionName(collectionId: Int) {
        viewModelScope.launch {
            repository.getCollectionName(collectionId).collect { name ->
                _collectionName.value = name
            }
        }
    }

}