package com.example.feature_home.home_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.domain.entity.CollectionWithCards
import com.example.database.domain.usecase.AddCollectionUseCase
import com.example.database.domain.usecase.DeleteCollectionByNameUseCase
import com.example.database.domain.usecase.GetAllCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UIState {
    data class AddCollection(val name: String) : UIState()
    data class DeleteCollection(val id: Int) : UIState()
}

data class HomeUiState(
    val listCollection: List<CollectionWithCards> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase,
    private val deleteCollectionByNameUseCase: DeleteCollectionByNameUseCase,
    private val addCollectionUseCase: AddCollectionUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val listCollectionFlow = getAllCollectionsUseCase.getAllCollections().stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                emptyList()
            )
            listCollectionFlow.collect{ listCollection ->
                _state.value = _state.value.copy(listCollection = listCollection)
            }
        }

    }

    fun reduce(intentAction: UIState){
        when(intentAction){
            is UIState.AddCollection -> {
                viewModelScope.launch {
                    addCollection(intentAction.name)
                }
            }
            is UIState.DeleteCollection -> {
                viewModelScope.launch {
                    deleteCollection(intentAction.id)
                }
            }
        }
    }
    private suspend fun deleteCollection(id: Int) {
        deleteCollectionByNameUseCase.deleteCollectionByName(id)
    }

    private suspend fun addCollection(name: String) {
        addCollectionUseCase.addCollection(name)
    }
}