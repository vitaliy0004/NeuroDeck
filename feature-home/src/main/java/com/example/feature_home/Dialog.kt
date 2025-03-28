package com.example.feature_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DeckDialog(){
    BasicAlertDialog(onDismissRequest = {}){
        Card(
            modifier = Modifier
                .height(150.dp)
                .width(100.dp)
                .padding(16.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextButton(onClick = { TODO()}) {
                    Row{
                        Icon(
                            painter = painterResource(id = R.drawable.app_registration_24px),
                            contentDescription = "Создание колоды",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                        Modifier.padding(8.dp)
                        Text(
                            "Создать колоду",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Monospace,
                                color = Color(red = 0, green = 0, blue = 0)
                            )

                        )
                    }
                }
                TextButton(onClick = { TODO() }) {
                    Row{
                        Modifier.padding(15.dp)
                        Icon(
                            painter = painterResource(id = R.drawable.file_open_24px),
                            contentDescription = "Импорт из файла",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                        Modifier.padding(8.dp)
                        Text(
                            "Импортировать",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily.Monospace,
                                color = Color(red = 0, green = 0, blue = 0)
                            )

                        )
                    }
                }

            }
        }
    }
}