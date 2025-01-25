package com.example.uas.repository

import com.example.uas.model.Film
import com.example.uas.model.Penayangan
//import com.example.uas.model.PenayanganDetailResponse
//import com.example.uas.model.PenayanganResponse
import com.example.uas.service_api.PenayanganService
import java.io.IOException

//mendeklarasikan operasi crud di kelas penayangan
interface PenayanganRepository {
    //suspend fun getPenayangan(): PenayanganResponse
    suspend fun getPenayangan(): List<Penayangan>
    suspend fun insertPenayangan(penayangan: Penayangan)
    suspend fun updatePenayangan(id_penayangan: String, penayangan: Penayangan)
    suspend fun deletePenayangan(id_penayangan: String)
    //suspend fun getPenayanganById(id_penayangan: String): PenayanganDetailResponse
    suspend fun getPenayanganById(id_penayangan: String): Penayangan
}

////berkomunikasi dengan API dan mengelola data penayangan di aplikasi
class NetworkPenayanganRepository(
    private val penayanganApiService: PenayanganService
) : PenayanganRepository {
    override suspend fun getPenayangan(): List<Penayangan> = penayanganApiService.getPenayangan()

    /*override suspend fun getPenayangan(): PenayanganResponse {
        return penayanganApiService.getPenayangan()
    }*/

    override suspend fun insertPenayangan(penayangan: Penayangan) {
        penayanganApiService.insertPenayangan(penayangan)
    }

    override suspend fun updatePenayangan(id_penayangan: String, penayangan: Penayangan) {
        penayanganApiService.updatePenayangan(id_penayangan, penayangan)
    }

    override suspend fun deletePenayangan(id_penayangan: String) {
        try {
            val response = penayanganApiService.deletePenayangan(id_penayangan)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Penayangan. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getPenayanganById(id_penayangan: String): Penayangan {
        return penayanganApiService.getPenayanganById(id_penayangan)
    }

    /*override suspend fun getPenayanganById(id_penayangan: String): PenayanganDetailResponse {
        return penayanganApiService.getPenayanganById(id_penayangan)
    }*/
}