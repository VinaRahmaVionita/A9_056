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

