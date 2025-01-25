package com.example.uas.repository

import com.example.uas.model.Film
import com.example.uas.service_api.FilmService
import okio.IOException

interface FilmRepository {
    suspend fun getFilm(): List<Film>
    suspend fun insertFilm(film: Film)
    suspend fun updateFilm(id_film:String, film: Film)
    suspend fun deleteFilm(id_film:String)
    suspend fun getFilmById(id_film:String): Film
}

