package com.example.uas.ui.viewmodel.Film

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Film
import com.example.uas.repository.FilmRepository
import kotlinx.coroutines.launch


fun Film.toUpdateFilmUiEvent(): UpdateFilmUiEvent = UpdateFilmUiEvent(
    id_film = id_film,
    judul_film = judul_film,
    durasi = durasi,
    deskripsi = deskripsi,
    genre = genre,
    rating_usia = rating_usia
)
//Mengubah objek UpdateTayangUiEvent menjadi Penayangan (database)
fun UpdateFilmUiEvent.toFilmEntity(): Film = Film(
    id_film = id_film,
    judul_film = judul_film,
    durasi = durasi,
    deskripsi = deskripsi,
    genre = genre,
    rating_usia = rating_usia
)
//menyimpan status ui
data class UpdateFilmUiState (
    val filmEvent: UpdateFilmUiEvent = UpdateFilmUiEvent(),
    val snackBarMessage: String? = null
)
//Data yang mewakili input pengguna
data class UpdateFilmUiEvent (
    val id_film: String = "",
    val judul_film: String = "",
    val durasi: String = "",
    val deskripsi: String = "",
    val genre: String = "",
    val rating_usia: String = ""
)