package com.example.uas.service_api

import com.example.uas.model.Penayangan
//import com.example.uas.model.PenayanganDetailResponse
//import com.example.uas.model.PenayanganResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PenayanganService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("bacapenayangan.php")
    suspend fun getPenayangan(): List<Penayangan>

    @GET("baca1penayangan.php/{id_penayangan}")
    suspend fun getPenayanganById(@Query("id_penayangan") id_penayangan: String): Penayangan

    @POST("insertpenayangan.php")
    suspend fun insertPenayangan(@Body penayangan: Penayangan)

    @PUT("editpenayangan.php/{id_penayangan}")
    suspend fun updatePenayangan(@Query("id_penayangan") id_penayangan: String, @Body penayangan: Penayangan)

    @DELETE("deletepenayangan.php/{id_penayangan}")
    suspend fun deletePenayangan(@Query("id_penayangan") id_penayangan: String): Response<Void>
}
