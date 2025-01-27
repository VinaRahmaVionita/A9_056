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


class DetailFilmViewModel (
    savedStateHandle: SavedStateHandle,
    private val filmRepository: FilmRepository
) : ViewModel() {
    private val id_film: String = checkNotNull(savedStateHandle[DestinasiDetailFilm.id_film])

    var detailFilmUiState: DetailFilmUiState by mutableStateOf(DetailFilmUiState())
        private set

    init {
        getFilmById()
    }

    private fun getFilmById(){
        viewModelScope.launch {
            detailFilmUiState = DetailFilmUiState(isLoading = true)
            try {
                val result = filmRepository.getFilmById(id_film)
                detailFilmUiState = DetailFilmUiState(
                    detailFilmUiEvent = result.toDetailFilmUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailFilmUiState = DetailFilmUiState (
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }

    fun deleteFilm(){
        viewModelScope.launch {
            detailFilmUiState = DetailFilmUiState(isLoading = true)
            try {
                filmRepository.deleteFilm(id_film)

                detailFilmUiState = DetailFilmUiState(isLoading = false)
            } catch (e: Exception){
                detailFilmUiState = DetailFilmUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }

}

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

