package com.games.freegameapp.di

import com.games.freegameapp.core.utils.Constants.BASE_URL
import com.games.freegameapp.data.remote.FreeGameApi
import com.games.freegameapp.data.repository.FreeGameRepositoryImpl
import com.games.freegameapp.domain.repository.FreeGamesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance() : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideFreeGameApi(retrofit: Retrofit) : FreeGameApi  = retrofit.create(FreeGameApi::class.java)


    @Provides
    @Singleton
    fun provideFreeGameRepository(freeGameApi: FreeGameApi) : FreeGamesRepository {
        return FreeGameRepositoryImpl(freeGameApi)
    }
}