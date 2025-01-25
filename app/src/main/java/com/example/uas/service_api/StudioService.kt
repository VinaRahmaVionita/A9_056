package com.example.uas.service_api

import com.example.uas.model.Studio
//import com.example.uas.model.StudioDetailResponse
//import com.example.uas.model.StudioResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface StudioService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    @GET("bacastudio.php")
    suspend fun getStudio(): List<Studio>

    @GET("baca1studio.php/{id_studio}")
    suspend fun getStudioById(@Query("id_studio") id_studio: String): Studio

    @POST("insertstudio.php")
    suspend fun insertStudio(@Body studio: Studio)

    @PUT("editstudio.php/{id_studio}")
    suspend fun updateStudio(@Query("id_studio") id_studio: String, @Body studio: Studio)

    @DELETE("deletestudio.php/{id_studio}")
    suspend fun deleteStudio(@Query("id_studio") id_studio: String): Response<Void>
}
