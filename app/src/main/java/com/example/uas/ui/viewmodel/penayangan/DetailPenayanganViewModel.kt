package com.example.uas.ui.viewmodel.penayangan

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Penayangan
import com.example.uas.repository.PenayanganRepository
import com.example.uas.ui.navigasi.DestinasiDetailPenayangan
import kotlinx.coroutines.launch



//memuat informasi tentang kondisi data penayangan
data class DetailTayangUiState(
    val detailTayangUiEvent: InsertTayangUiEvent = InsertTayangUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailTayangUiEvent == InsertTayangUiEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailTayangUiEvent != InsertTayangUiEvent()
}

//Mengubah objek penayangan menjadi InsertStudioUiEvent untuk ditampilkan pada UI
fun Penayangan.toDetailPenayanganUiEvent(): InsertTayangUiEvent{
    return InsertTayangUiEvent(
        id_penayangan = id_penayangan,
        id_film = id_film,
        id_studio = id_studio,
        tanggal_penayangan = tanggal_penayangan,
        harga_tiket = harga_tiket
    )
}