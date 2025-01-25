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

//berkomunikasi dengan API dan mengelola data studio di aplikasi
class NetworkTiketRepository(
    private val tiketApiService: TiketService
) : TiketRepository {
    override suspend fun getTiket(): List<Tiket> = tiketApiService.getTiket()
    override suspend fun getTiketById(id_tiket: String): Tiket {
        return tiketApiService.getTiketById(id_tiket)
    }

    /*override suspend fun getTiket(): TiketResponse {
        return tiketApiService.getTiket()
    }*/

    /*override suspend fun getTiketById(id_tiket: String): TiketDetailResponse {
        return tiketApiService.getTiketById(id_tiket)
    }*/


    override suspend fun insertTiket(tiket: Tiket) {
        tiketApiService.insertTiket(tiket)
    }

    override suspend fun updateTiket(id_tiket: String, tiket: Tiket) {
        tiketApiService.updateTiket(id_tiket, tiket)
    }

    override suspend fun deleteTiket(id_tiket: String) {
        try {
            val response = tiketApiService.deleteTiket(id_tiket)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete Tiket. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }
}