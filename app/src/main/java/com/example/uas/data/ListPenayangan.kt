package com.example.uas.data

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.ui.viewmodel.Film.HomeFilmUiState
import com.example.uas.ui.viewmodel.Film.HomeFilmViewModel
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.penayangan.HomePenayanganUiState
import com.example.uas.ui.viewmodel.penayangan.HomePenayanganViewModel

object Listtayang {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @Composable
    fun listtayang(
        homePenayanganViewModel: HomePenayanganViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ) : List<String> {
        val penayanganUiState = homePenayanganViewModel.tayangUiState

        return when (penayanganUiState){
            is HomePenayanganUiState.Success -> {
                penayanganUiState.penayangan.map { it.id_penayangan }
            }
            else -> emptyList()
        }
    }
}