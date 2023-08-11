package com.games.freegameapp.data.repository

import com.games.freegameapp.core.common.Resource
import com.games.freegameapp.data.remote.FreeGameApi
import com.games.freegameapp.data.remote.mapper.toDomainFreeGames
import com.games.freegameapp.domain.model.FreeGames
import com.games.freegameapp.domain.repository.FreeGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FreeGameRepositoryImpl @Inject constructor(private val freeGameApi: FreeGameApi) :
    FreeGamesRepository {

    override fun getFreeGames(): Flow<Resource<List<FreeGames>>> = flow {
        emit(Resource.Loading())
        val result = freeGameApi.getFreeGame().map {
            it.toDomainFreeGames()
        }
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

}