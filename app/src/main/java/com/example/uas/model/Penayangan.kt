package com.example.uas.model

import kotlinx.serialization.Serializable

/*@Serializable
data class PenayanganResponse(
    val status: Boolean,
    val message: String,
    val data: List<Penayangan>
)

@Serializable
data class PenayanganDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Penayangan
)*/

//merepresentasikan objek penayangan
@Serializable
data class Penayangan(
    val id_penayangan: String,
    val id_film: String,
    val id_studio: String,
    val tanggal_penayangan: String,
    val harga_tiket: String
)
