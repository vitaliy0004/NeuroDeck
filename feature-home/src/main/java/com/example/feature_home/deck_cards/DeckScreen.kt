package com.example.feature_home.deck_cards

import androidx.compose.foundation.background
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DeckScreen(
    collectionId: Int,
    viewModel: DeckViewModel = hiltViewModel()
) {
    val cards by viewModel.cards.collectAsState()
    val name by viewModel.collectionName.collectAsState()

    LaunchedEffect(collectionId) {
        viewModel.loadCards(collectionId)
        viewModel.loadCollectionName(collectionId)
    }
    Column(modifier = Modifier.fillMaxSize().background(Color.Cyan)) {
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(cards) { card ->
                CardItem(
                    card = card,
                    onCardClick = { clickedCard ->
                        viewModel.toggleCardExpansion(clickedCard)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDeckScreen() {
    FakeDeckScreen()
}

@Composable
fun FakeDeckScreen() {
    // Фейковые данные
    val fakeCards = listOf(
        Card(1, "Вопрос 1", "Ответ 1", false),
        Card(2, "Вопрос 2", "Ответ 2", true),
        Card(3, "Вопрос 3", "Ответ 3", false)
    )
    val fakeName = "Пример коллекции"

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF1AF7D))) {
        Text(
            text = fakeName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(fakeCards) { card ->
                CardItem(
                    card = card,
                    onCardClick = { /* Ничего не делаем в превью */ }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
