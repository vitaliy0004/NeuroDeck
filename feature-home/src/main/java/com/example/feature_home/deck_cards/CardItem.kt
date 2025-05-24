package com.example.feature_home.deck_cards

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardItem(
    card: Card,
    onCardClick: (Card) -> Unit,
    onEditClick: (Card) -> Unit = {}
) {
    val animatedHeight by animateDpAsState(
        targetValue = if (card.isExpanded) 250.dp else 120.dp,
        animationSpec = tween(durationMillis = 400)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .clickable { onCardClick(card) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xBCF8F8F8)), // Полупрозрачный белый
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    text = card.question,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                // Разделитель
                if (card.isExpanded) {
                    Divider(
                        color = Color.Gray.copy(alpha = 0.5f),
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = card.answer,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Иконка редактирования
            IconButton(
                onClick = { onEditClick(card) },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Collapsed Card")
@Composable
fun PreviewCollapsedCard() {
    CardItem(
        card = Card(
            id = 1,
            question = "Что такое Jetpack Compose?",
            answer = "Фреймворк для UI...",
            isExpanded = false
        ), onCardClick = {})
}

@Preview(showBackground = true, name = "Expanded Card")
@Composable
fun PreviewExpandedCard() {
    CardItem(
        card = Card(
            id = 2,
            question = "Что такое LazyColumn?",
            answer = "Аналог RecyclerView...",
            isExpanded = true
        ), onCardClick = {})
}