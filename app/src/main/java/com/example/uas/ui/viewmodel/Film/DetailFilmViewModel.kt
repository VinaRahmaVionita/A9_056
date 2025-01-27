package com.example.uas.ui.viewmodel.Film

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Film
import com.example.uas.repository.FilmRepository
import com.example.uas.ui.navigasi.DestinasiDetailFilm
import kotlinx.coroutines.launch




data class DetailFilmUiState(
    val detailFilmUiEvent: InsertFilmUiEvent = InsertFilmUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
){
    val isFilmUiEventEmpty: Boolean
        get() = detailFilmUiEvent == InsertFilmUiEvent()

    val isFilmUiEventNotEmpty: Boolean
        get() = detailFilmUiEvent != InsertFilmUiEvent()
}
//Mengkonversi objek Film menjadi InsertFilmUiEvent(UI)
fun Film.toDetailFilmUiEvent(): InsertFilmUiEvent {
    return InsertFilmUiEvent(
        id_film = id_film,
        judul_film = judul_film,
        durasi = durasi,
        deskripsi = deskripsi,
        genre = genre,
        rating_usia = rating_usia
    )
}

