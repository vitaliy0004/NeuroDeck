package com.example.feature_home.deck_cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_home.deck_cards.model.DeckIntent
import com.example.feature_home.deck_cards.model.DeckEffect
import com.example.feature_home.deck_cards.model.DeckUiState
import com.example.feature_home.deck_cards.model.Card
@Composable
fun DeckScreen(
    collectionId: Int,
    viewModel: DeckViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.sendIntent(DeckIntent.LoadCollection(collectionId))
        viewModel.effect.collect { effect ->
            when (effect) {
                is DeckEffect.ShowError -> {
                    snackbarHostState.showSnackbar(effect.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(padding)) {
            Text(
                text = uiState.collectionName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                items(uiState.cards) { card ->
                    CardItem(
                        card = card,
                        onCardClick = { viewModel.sendIntent(DeckIntent.ToggleCard(it)) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FakeDeckScreen() {
    val fakeState = DeckUiState(
        collectionName = "Пример коллекции",
        cards = listOf(
            Card(1, "Вопрос 1", "Ответ 1", false),
            Card(2, "Вопрос 2", "Ответ 2\nблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблаблабла", true),
            Card(3, "Вопрос 3", "Ответ 3", false)
        )
    )

    Scaffold(
        snackbarHost = { SnackbarHost(remember { SnackbarHostState() }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF1AF7D))
                .padding(padding)
        ) {
            Text(
                text = fakeState.collectionName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp).fillMaxWidth()
            )
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                items(fakeState.cards) { card ->
                    CardItem(card = card, onCardClick = {})
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
