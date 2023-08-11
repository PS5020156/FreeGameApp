package com.games.freegameapp.domain.usecase

import com.games.freegameapp.domain.repository.FreeGamesRepository
import javax.inject.Inject

class FreeGameUseCase @Inject constructor(private val repository: FreeGamesRepository) {

    operator fun invoke() = repository.getFreeGames()
}