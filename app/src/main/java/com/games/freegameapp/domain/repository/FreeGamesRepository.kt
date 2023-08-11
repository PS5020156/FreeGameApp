package com.games.freegameapp.domain.repository

import com.games.freegameapp.core.common.Resource
import com.games.freegameapp.domain.model.FreeGames
import kotlinx.coroutines.flow.Flow

interface FreeGamesRepository {
    fun getFreeGames() : Flow<Resource<List<FreeGames>>>
}