package com.zehcort.factico.views.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zehcort.factico.viewmodels.FactsViewModel
import com.zehcort.factico.views.composables.components.LoadingIndicator
import kotlin.random.Random

@Composable
fun HomeScreen(
    viewModel: FactsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val errorMessage = state.errorMessage

    if (state.isLoading) {
        LoadingIndicator()
    } else {
        if (errorMessage.isNullOrEmpty()) {
            state.currentFact?.let { fact ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = getRandomColor())
                        .padding(vertical = 16.dp)
                        .clickable { viewModel.fetchRandomFact() },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = fact.text,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )

                        Text(
                            modifier = Modifier.padding(top = 16.dp),
                            text = "Source: ${fact.source}",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = errorMessage,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                IconButton(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = viewModel::fetchRandomFact
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchRandomFact()
    }
}

private fun getRandomColor(): Color = Color(
    Random.nextFloat(),
    Random.nextFloat(),
    Random.nextFloat(),
    1F
)