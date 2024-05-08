package com.example.degreemanagment

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.io.BufferedReader
import java.io.File
import java.io.IOException

@Composable
fun MainScreen(navController: NavHostController) {

    var numberOfCards by remember { mutableStateOf(1) }
    var cardTexts by remember { mutableStateOf<List<String>>(emptyList()) }
    val context: Context = LocalContext.current

    // LOAD DATA FROM FILE TO CARDS
    LaunchedEffect(key1 = numberOfCards) {
        val fileName = "data3.txt"
        try {
            val file = File(context.filesDir, fileName)
            val bufferedReader: BufferedReader = file.bufferedReader()
            cardTexts = bufferedReader.readLines()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // MAIN
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(bottom = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "My Degrees",
            fontSize = 26.sp
        )

        // DYNAMIC CARDS
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            items(cardTexts.size) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            navController.navigate("thirdScreen/$index")
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFD4E6F1)) // ARGB
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(15.dp)
                                .align(Alignment.CenterHorizontally),
                            text = cardTexts[index],
                            fontSize = 22.sp
                        )
                    }
                }
            }
        }
    }
    // BUTTON
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = {
                navController.navigate("secondScreen")
                numberOfCards++
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "NEW",
                fontSize = 20.sp
            )
        }
    }
}
