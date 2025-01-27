package com.example.uas.ui.viewmodel.Film

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Film
import com.example.uas.repository.FilmRepository
import kotlinx.coroutines.launch
import java.io.IOException



//kelas ViewModel yang digunakan untuk mengelola data dan logika terkait film.
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeFilmViewModel (private val film: FilmRepository) : ViewModel() {
    var filmUiState: HomeFilmUiState by mutableStateOf(HomeFilmUiState.Loading)
        private set

    init {
        getFilm()
    }
    //Mendapatkan detail data penayangan berdasarkan id_penayangan
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getFilm() {
        viewModelScope.launch {
            filmUiState = HomeFilmUiState.Loading
            filmUiState = try {
                HomeFilmUiState.Success(film.getFilm())
            } catch (e: IOException){
                HomeFilmUiState.Error
            } catch (e: HttpException){
                HomeFilmUiState.Error
            }
        }
    }
    //Menghapus data penayangan berdasarkan id_penayangan
    fun deleteFilm(id_film: String) {
        viewModelScope.launch {
            try {
                film.deleteFilm(id_film)
            } catch (e: IOException){
                HomeFilmUiState.Error
            } catch (e:HttpException){
                HomeFilmUiState.Error
            }
        }
    }
}