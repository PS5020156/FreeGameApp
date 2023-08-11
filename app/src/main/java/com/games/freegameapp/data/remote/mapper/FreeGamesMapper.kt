package com.games.freegameapp.data.remote.mapper

import com.games.freegameapp.data.remote.dto.FreeGamesDto
import com.games.freegameapp.domain.model.FreeGames

fun FreeGamesDto.toDomainFreeGames() : FreeGames {
    return FreeGames(gameUrl, id, shortDescription, thumbnail, title)
}