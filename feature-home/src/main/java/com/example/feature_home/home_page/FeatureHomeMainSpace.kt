package com.example.feature_home.home_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun FeatureHomeMainSpace(
    onNavigateToDeckCard: () -> Unit
) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val state by homeViewModel.state.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) DeckDialog(
        homeViewModel = homeViewModel,
        onDismiss = { showDialog = false }
    )
    LazyColumn(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        items(state.listCollection) { deckName ->
            Spacer(modifier = Modifier.height(8.dp))
            DeckDisplay(onNavigateToDeckCard)
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            TextButton(onClick = {
                showDialog = true
            }) {
                Row {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Иконка добавления колоды",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(38.dp)
                    )
                    Text(
                        "Добавить колоду",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color.DarkGray
                        )

                    )
                }
            }
        }

    }
}