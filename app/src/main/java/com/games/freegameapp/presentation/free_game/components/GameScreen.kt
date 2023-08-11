package com.games.freegameapp.presentation.free_game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.games.freegameapp.presentation.free_game.state.FreeGameState

@Composable
fun GameScreen(freeGameState: FreeGameState, modifier: Modifier) {

    if (freeGameState.freeGames?.isNotEmpty()!!) {
        LazyColumn {
            items(freeGameState.freeGames) {
                FreeGameItem(modifier, it)
            }
        }
    } else if (freeGameState.isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    }
}