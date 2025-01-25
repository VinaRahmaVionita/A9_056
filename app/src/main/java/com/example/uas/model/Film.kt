package com.example.uas.model

import android.provider.ContactsContract.RawContacts.Data
import kotlinx.serialization.Serializable

/*@Serializable
data class FilmRespone(
    val status: Boolean,
    val message: String,
    val data: List<Film>
)

@Serializable
data class FilmDetailRespone(
    val status: Boolean,
    val message: String,
    val data: Film
)*/

//merepresentasikan objek film
@Serializable
data class Film(
    val id_film: String,
    val judul_film: String,
    val durasi: String,
    val deskripsi: String,
    val genre: String,
    val rating_usia: String
)