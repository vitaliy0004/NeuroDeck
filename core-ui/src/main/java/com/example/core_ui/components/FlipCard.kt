package com.example.core_ui.components

import androidx.compose.ui.graphics.Color
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
fun FlipCard(
    frontContent: @Composable () -> Unit,
    backContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    cardColor: Color = Color.White.copy(alpha = 0.2f), // Полупрозрачный эффект
    cornerRadius: Int = 16, // Закругление углов
    elevation: Int = 8 // Тень

) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = modifier
            .aspectRatio(1.5f) // Соотношение сторон карточки
            .clickable { isFlipped = !isFlipped }
            .graphicsLayer { rotationY = rotation }
            .shadow(elevation.dp, RoundedCornerShape(cornerRadius.dp))
            .clip(RoundedCornerShape(cornerRadius.dp))
    ) {
        if (rotation < 90f) {
            GlassCard(color = cardColor) { frontContent() }
        } else {
            Box(Modifier.graphicsLayer { rotationY = 180f }) {
                GlassCard(color = cardColor) { backContent() }
            }
        }
    }
}

@Composable
fun GlassCard(
    color: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}




@Preview(showBackground = true)
@Composable
fun FlipCardPreview() {
    MaterialTheme {
        FlipCard(
            frontContent = {
                Text(
                    text = "Hello",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            backContent = {
                Text(
                    text = "Привет",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}
