package com.example.uas.repository

import com.example.uas.model.Studio
//import com.example.uas.model.StudioDetailResponse
//import com.example.uas.model.StudioResponse
import com.example.uas.service_api.StudioService
import java.io.IOException

///mendeklarasikan operasi crud di kelas studio
interface StudioRepository {
    //suspend fun getStudio(): StudioResponse
    suspend fun getStudio(): List<Studio>
    suspend fun insertStudio(studio: Studio)
    suspend fun updateStudio(id_studio: String, studio: Studio)
    suspend fun deleteStudio(id_studio: String)
    //suspend fun getStudioById(id_studio: String): StudioDetailResponse
    suspend fun getStudioById(id_studio: String): Studio
}

//berkomunikasi dengan API dan mengelola data studio di aplikasi
class NetworkStudioRepository(
    private val studioApiService: StudioService
) : StudioRepository {
    override suspend fun getStudio(): List<Studio> = studioApiService.getStudio()

    /*override suspend fun getStudio(): StudioResponse {
        return studioApiService.getStudio()
    }*/

    override suspend fun insertStudio(studio: Studio) {
        studioApiService.insertStudio(studio)
    }

    override suspend fun updateStudio(id_studio: String, studio: Studio) {
        studioApiService.updateStudio(id_studio, studio)
    }

    override suspend fun deleteStudio(id_studio: String) {
        try {
            val response = studioApiService.deleteStudio(id_studio)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Studio. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getStudioById(id_studio: String): Studio {
        return studioApiService.getStudioById(id_studio)
    }

    /*override suspend fun getStudioById(id_studio: String): StudioDetailResponse {
        return studioApiService.getStudioById(id_studio)
    }*/
}