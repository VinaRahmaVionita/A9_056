package com.example.uas.ui.viewmodel.Studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Studio
import com.example.uas.repository.StudioRepository
import kotlinx.coroutines.launch


//menyimpan status ui
data class UpdateStudioUiState(
    val studioEvent: UpdateStudioUiEvent = UpdateStudioUiEvent(),
    val snackBarMessage: String? = null
)
//Data yang mewakili input pengguna, seperti id_studio, nama_studio, dan kapasitas
data class UpdateStudioUiEvent(
    val id_studio: String = "",
    val nama_studio: String = "",
    val kapasitas: String = ""
)