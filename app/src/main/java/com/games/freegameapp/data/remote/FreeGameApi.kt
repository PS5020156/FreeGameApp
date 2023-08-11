package com.games.freegameapp.data.remote

import com.games.freegameapp.data.remote.dto.FreeGamesDto
import retrofit2.http.GET

interface FreeGameApi {

    @GET("games")
    suspend fun getFreeGame() : List<FreeGamesDto>
}