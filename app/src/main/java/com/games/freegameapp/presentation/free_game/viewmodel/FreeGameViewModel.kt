package com.games.freegameapp.presentation.free_game.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.games.freegameapp.core.common.Resource
import com.games.freegameapp.domain.usecase.FreeGameUseCase
import com.games.freegameapp.presentation.free_game.state.FreeGameState
import com.games.freegameapp.presentation.free_game.state.UiEffect
import com.games.freegameapp.presentation.free_game.state.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FreeGameViewModel @Inject constructor(private val useCase: FreeGameUseCase) : ViewModel() {

    private val _freeGameState = MutableStateFlow(FreeGameState())

    val freeGameState: StateFlow<FreeGameState>
        get() = _freeGameState

    private val _uiEffect  = MutableSharedFlow<UiEffect>()

    val uiEffect: SharedFlow<UiEffect>
        get() = _uiEffect.asSharedFlow()

    init {
        getAllFreeGames()
    }

    private fun getAllFreeGames() = useCase().onEach {
        when(it) {
            is Resource.Error -> {
                _freeGameState.value = FreeGameState().copy(errorMsg = it.msg)
                _uiEffect.emit(UiEffect.ShowSnackBar(it.msg.toString()))
            }
            is Resource.Loading -> {
                _freeGameState.value = FreeGameState().copy(isLoading = true)
            }
            is Resource.Success -> {
                _freeGameState.value = FreeGameState().copy(freeGames = it.data)
            }
        }
    }.launchIn(viewModelScope)


    fun onEvent(uiEvent: UiEvent) {
        when(uiEvent) {
            UiEvent.NavigateToDetailScreen -> {

            }
        }
    }
}