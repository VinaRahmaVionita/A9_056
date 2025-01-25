package com.example.uas.service_api

import com.example.uas.model.Film
//import com.example.uas.model.FilmDetailRespone
//import com.example.uas.model.FilmRespone
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

//metode HTTP untuk mengelola data film
interface FilmService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("bacafilm.php")
    suspend fun getFilm(): List<Film>

    @GET("baca1film.php/{id_film}")
    suspend fun getFilmById(@Query("id_film") id_film: String): Film

    @POST("insertfilm.php")
    suspend fun insertFilm(@Body film: Film)

    @PUT("editfilm.php/{id_film}")
    suspend fun updateFilm(@Query("id_film") id_film: String, @Body film: Film)

    @DELETE("deletefilm.php/{id_film}")
    suspend fun deleteFilm(@Query("id_film") id_film: String): Response<Void>
}