package com.example.uas.data

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.Studio.HomeStudioUiState
import com.example.uas.ui.viewmodel.Studio.HomeStudioViewModel

object studioList {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    @Composable
    fun liststudio(
        homeStudioViewModel: HomeStudioViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        // Akses langsung nilai `filmUiState` dari ViewModel
        val studioUiState = homeStudioViewModel.stdUiState
         return when (studioUiState){
             is HomeStudioUiState.Success -> {
                 studioUiState.studio.map { it.id_studio }
             }
             else -> emptyList()
         }
    }
}