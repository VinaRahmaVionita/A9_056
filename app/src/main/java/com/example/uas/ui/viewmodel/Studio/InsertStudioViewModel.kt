package com.example.uas.ui.viewmodel.Studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Studio
import com.example.uas.repository.StudioRepository
import kotlinx.coroutines.launch

//Mengelola logika aplikasi untuk operasi penambahan dan pembaruan data studio

//Mengubah state UI (InsertStudioUiEvent) menjadi objek Studio untuk disimpan ke database
fun InsertStudioUiEvent.toStd(): Studio = Studio(
    id_studio = id_studio,
    nama_studio = nama_studio,
    kapasitas = kapasitas
)
//Mengubah objek Studio menjadi event UI (InsertStudioUiEvent)
fun Studio.toInsertStudioUiEvent(): InsertStudioUiEvent = InsertStudioUiEvent(
    id_studio = id_studio,
    nama_studio = nama_studio,
    kapasitas = kapasitas
)
//Menyimpan state layar (UI), khususnya data input yang sedang diisi pengguna
data class InsertStudioUiState(
    val insertStudioUiEvent: InsertStudioUiEvent = InsertStudioUiEvent()
)
//Mewakili data input pengguna
data class InsertStudioUiEvent(
    val id_studio: String = "",
    val nama_studio: String = "",
    val kapasitas: String = ""
)