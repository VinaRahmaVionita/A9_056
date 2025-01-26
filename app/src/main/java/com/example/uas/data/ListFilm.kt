package com.example.uas.data

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.ui.viewmodel.Film.HomeFilmUiState
import com.example.uas.ui.viewmodel.Film.HomeFilmViewModel
import com.example.uas.ui.viewmodel.PenyediaViewModel

object filmList {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @Composable
    fun listfilm(
        homeFilmViewModel: HomeFilmViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ) : List<String> {
        val filmUiState = homeFilmViewModel.filmUiState

        return when (filmUiState){
            is HomeFilmUiState.Success -> {
                filmUiState.film.map { it.id_film }
            }
            else -> emptyList()
        }
    }
}
