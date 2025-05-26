package com.example.feature_home.deck_cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.domain.RepositoryDatabase
import com.example.feature_home.deck_cards.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    private val repository: RepositoryDatabase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DeckUiState())
    val uiState: StateFlow<DeckUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<DeckEffect>()
    val effect: SharedFlow<DeckEffect> = _effect.asSharedFlow()

    private val intentChannel = Channel<DeckIntent>(Channel.UNLIMITED)

    init {
        processIntents()
    }

    fun sendIntent(intent: DeckIntent) {
        viewModelScope.launch { intentChannel.send(intent) }
    }

    private fun processIntents() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect { intent ->
                when (intent) {
                    is DeckIntent.LoadCollection -> loadCollection(intent.collectionId)
                    is DeckIntent.ToggleCard -> toggleCard(intent.card)
                }
            }
        }
    }

    private fun loadCollection(collectionId: Int) {
        viewModelScope.launch {
            runCatching {
                val cards = repository.getCardsForCollection(collectionId)
                    .first()
                    .map { Card(it.id, it.question, it.answer) }
                val name = repository.getCollectionName(collectionId).firstOrNull() ?: "Без имени"
                _uiState.value = DeckUiState(collectionName = name, cards = cards)
            }.onFailure {
                _effect.emit(DeckEffect.ShowError("Ошибка загрузки коллекции"))
            }
        }
    }

    private fun toggleCard(card: Card) {
        val updatedCards = _uiState.value.cards.map {
            if (it.id == card.id) it.copy(isExpanded = !it.isExpanded) else it
        }
        _uiState.value = _uiState.value.copy(cards = updatedCards)
    }
}
