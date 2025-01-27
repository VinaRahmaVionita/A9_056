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