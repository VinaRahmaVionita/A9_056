package com.example.uas.ui.viewmodel.Film

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Film
import com.example.uas.repository.FilmRepository
import kotlinx.coroutines.launch


fun InsertFilmUiEvent.toFilm(): Film = Film(
    id_film = id_film,
    judul_film = judul_film,
    durasi = durasi,
    deskripsi = deskripsi,
    genre = genre,
    rating_usia = rating_usia
)

fun Film.toInsertFilmUiEvent(): InsertFilmUiEvent = InsertFilmUiEvent(
    id_film = id_film,
    judul_film = judul_film,
    durasi = durasi,
    deskripsi = deskripsi,
    genre = genre,
    rating_usia = rating_usia
)

// Data class untuk UI State dan Event
data class InsertFilmUiState(
    val insertFilmUiEvent: InsertFilmUiEvent = InsertFilmUiEvent()
)

data class InsertFilmUiEvent(
    val id_film: String = "",
    val judul_film: String = "",
    val durasi: String = "",
    val deskripsi: String = "",
    val genre: String = "",
    val rating_usia: String = ""
)