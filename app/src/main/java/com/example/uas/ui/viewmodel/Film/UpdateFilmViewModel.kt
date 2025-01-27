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

class UpdateFilmViewModel (
    private val repository: FilmRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(UpdateFilmUiState())
        private set

    private val _id_film: String = checkNotNull(savedStateHandle["id_film"])

    init {
        getFilmDetail()
    }

    private fun getFilmDetail() {
        viewModelScope.launch {
            try {
                val film = repository.getFilmById(_id_film)
                uiState = UpdateFilmUiState(filmEvent = film.toUpdateFilmUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateFilmState(filmEvent: UpdateFilmUiEvent) {
        uiState = uiState.copy(filmEvent = filmEvent)
    }

    fun updateFilm() {
        val currentEvent = uiState.filmEvent

        viewModelScope.launch {
            try {
                repository.updateFilm(currentEvent.id_film, currentEvent.toFilmEntity())
                uiState = uiState.copy(
                    snackBarMessage = "Data berhasil diperbarui",
                    filmEvent = UpdateFilmUiEvent()
                )
            } catch (e: Exception){
                uiState = uiState.copy(
                    snackBarMessage = "Data gagal diperbarui"
                )
            }
        }
    }

    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

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