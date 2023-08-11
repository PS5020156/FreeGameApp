package com.games.freegameapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.games.freegameapp.presentation.free_game.components.GameScreen
import com.games.freegameapp.presentation.free_game.state.UiEffect
import com.games.freegameapp.presentation.free_game.viewmodel.FreeGameViewModel
import com.games.freegameapp.presentation.navigation.screens.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(navController: NavHostController) {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState)}) {
        NavHost(navController = navController, startDestination = Screen.GameScreen.route, modifier = Modifier.padding(it)) {

            composable(Screen.GameScreen.route) {
                val freeGameViewModel = hiltViewModel<FreeGameViewModel>()
                val state = freeGameViewModel.freeGameState.collectAsStateWithLifecycle()

                LaunchedEffect(key1 = true) {
                    freeGameViewModel.uiEffect.collectLatest {uiEffect->
                        when (uiEffect) {
                            UiEffect.NavigateToDetailScreen -> {
                                TODO()
                            }
                            is UiEffect.ShowSnackBar -> {
                                launch {
                                    snackBarHostState.showSnackbar(uiEffect.msg, duration = SnackbarDuration.Long)
                                }
                            }
                        }
                    }
                }
                GameScreen(freeGameState = state.value, modifier = Modifier)
            }

        }
    }

}