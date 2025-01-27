package com.example.uas.ui.viewmodel.penayangan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Penayangan
import com.example.uas.repository.PenayanganRepository
import kotlinx.coroutines.launch


//Mengonversi objek InsertTayangUiEvent menjadi Penayangan
fun InsertTayangUiEvent.toTayang(): Penayangan = Penayangan(
    id_penayangan = id_penayangan,
    id_film = id_film,
    id_studio = id_studio,
    tanggal_penayangan = tanggal_penayangan,
    harga_tiket = harga_tiket
)
//Mengonversi objek Penayangan menjadi InsertTayangUiEvent
fun Penayangan.toInsertTayangUiEvent(): InsertTayangUiEvent = InsertTayangUiEvent(
    id_penayangan = id_penayangan,
    id_film = id_film,
    id_studio = id_studio,
    tanggal_penayangan = tanggal_penayangan,
    harga_tiket = harga_tiket
)
//Data penayangan yang akan ditambahkan atau diperbarui
data class InsertTayangUiState(
    val insertTayangUiEvent: InsertTayangUiEvent = InsertTayangUiEvent()
)
//Berisi data untuk operasi insert/update
data class InsertTayangUiEvent(
    val id_penayangan: String = " ",
    val id_film: String = " ",
    val id_studio: String = " ",
    val tanggal_penayangan: String = " ",
    val harga_tiket: String = " "
)