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


//Mengubah objek Studio menjadi InsertStudioUiEvent untuk ditampilkan pada UI
fun Studio.toDetailStudioUiEvent(): InsertStudioUiEvent{
    return InsertStudioUiEvent(
        id_studio = id_studio,
        nama_studio = nama_studio,
        kapasitas = kapasitas
    )
}