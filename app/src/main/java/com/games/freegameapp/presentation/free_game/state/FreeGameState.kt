package com.games.freegameapp.presentation.free_game.state

import com.games.freegameapp.domain.model.FreeGames

data class FreeGameState(
 val freeGames: List<FreeGames>? = emptyList(),
 val errorMsg: String? = "",
 val isLoading: Boolean = false
)