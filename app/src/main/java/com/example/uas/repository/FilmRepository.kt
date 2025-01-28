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

//berkomunikasi dengan API dan mengelola data film di aplikasi
class NetworkFilmRepository (
    private val filmApiService: FilmService
) : FilmRepository {
    override suspend fun getFilm(): List<Film> = filmApiService.getFilm()

    override suspend fun insertFilm(film: Film) {
        filmApiService.insertFilm(film)
    }

    override suspend fun updateFilm(id_film: String, film: Film) {
        filmApiService.updateFilm(id_film, film)
    }

    override suspend fun deleteFilm(id_film: String) {
        try {
            val response = filmApiService.deleteFilm(id_film)
            if (!response.isSuccessful){
                throw IOException("Failed to delete Film. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getFilmById(id_film: String): Film {
        return filmApiService.getFilmById(id_film)
    }

}