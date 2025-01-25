package com.example.uas.model

import kotlinx.serialization.Serializable

/*@Serializable
data class TiketResponse(
    val status: Boolean,
    val message: String,
    val data: List<Tiket>
)

@Serializable
data class TiketDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Tiket
)*/
//merepresentasikan objek tiket
@Serializable
data class Tiket(
    val id_tiket: String,
    val id_penayangan: String,
    val jumlah_tiket: String,
    val total_harga: String,
    val status_pembayaran: String
)