package com.example.feature_home.home_page

import android.os.Bundle
import android.view.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.fragment.app.Fragment
import com.example.feature_home.navigation.navigation


class FeatureHomeFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme {
                    TopBar()
                    FeatureHomeMainSpace()
                }
            }
        }
    }
}


@Preview
@Composable
fun FeatureHomeMainSpace(){
    val navController = navigation()
    var decks by remember { mutableStateOf<List<String>>(listOf()) }
    LazyColumn(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item {
            TextButton(onClick = {
                decks = decks + "Новая колода ${decks.size + 1}"
            }) {
                Row {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Иконка добавления колоды",
                        tint = Color.White,
                        modifier = Modifier.size(38.dp)
                    )
                    Text(
                        "Добавить колоду",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color(red = 255, green = 255, blue = 255)
                        )

                    )
                }
            }
        }
        items(decks) { deckName ->
            Spacer(modifier = Modifier.height(8.dp))
            DeckDisplay(navController = navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBar(){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    CenterAlignedTopAppBar(
        modifier = Modifier.height(90.dp),
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Box(
                contentAlignment = Center
            ){
                Text(
                    "Neurodeck",
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 37.sp,
                    fontFamily = FontFamily.Monospace
                )
            }
        },
        actions = {
            Box(
                contentAlignment = Alignment.TopEnd
            ){
                IconButton(onClick = { TODO() }) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Настройки"
                    )
                }

               }

        },
        scrollBehavior = scrollBehavior
    )
}