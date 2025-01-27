package com.example.uas.ui.viewmodel.Studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Studio
import com.example.uas.repository.StudioRepository
import com.example.uas.ui.navigasi.DestinasiDetailStudio
import kotlinx.coroutines.launch


//memuat informasi tentang kondisi data
data class DetailStudioUiState(
    val detailStudioUiEvent: InsertStudioUiEvent = InsertStudioUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailStudioUiEvent == InsertStudioUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailStudioUiEvent != InsertStudioUiEvent()
}
//Mengubah objek Studio menjadi InsertStudioUiEvent untuk ditampilkan pada UI
fun Studio.toDetailStudioUiEvent(): InsertStudioUiEvent{
    return InsertStudioUiEvent(
        id_studio = id_studio,
        nama_studio = nama_studio,
        kapasitas = kapasitas
    )
}