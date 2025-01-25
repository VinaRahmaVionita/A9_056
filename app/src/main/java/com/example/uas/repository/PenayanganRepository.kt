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

