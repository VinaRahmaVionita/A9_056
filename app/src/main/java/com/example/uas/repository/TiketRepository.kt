package com.example.uas.repository

import com.example.uas.model.Tiket
//import com.example.uas.model.TiketDetailResponse
//import com.example.uas.model.TiketResponse
import com.example.uas.service_api.TiketService
import java.io.IOException

//mendeklarasikan operasi crud di kelas tiket
interface TiketRepository {
    suspend fun getTiket(): List<Tiket>
    suspend fun getTiketById(id_tiket: String): Tiket
    suspend fun insertTiket(tiket: Tiket)
    suspend fun updateTiket(id_tiket: String, tiket: Tiket)
    suspend fun deleteTiket(id_tiket: String)
}

