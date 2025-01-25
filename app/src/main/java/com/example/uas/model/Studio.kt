package com.example.uas.model

import kotlinx.serialization.Serializable

/*@Serializable
data class StudioResponse(
    val status: Boolean,
    val message: String,
    val data: List<Studio>
)

@Serializable
data class StudioDetailResponse(
    val status: Boolean,
    val message: String,
    val data: Studio
)*/
//merepresentasikan objek studio
@Serializable
data class Studio(
    val id_studio: String,
    val nama_studio: String,
    val kapasitas: String
)