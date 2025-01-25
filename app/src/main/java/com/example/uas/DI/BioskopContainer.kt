package com.example.uas.DI

import com.example.uas.repository.FilmRepository
import com.example.uas.repository.NetworkFilmRepository
import com.example.uas.repository.NetworkPenayanganRepository
import com.example.uas.repository.NetworkStudioRepository
import com.example.uas.repository.NetworkTiketRepository
import com.example.uas.repository.PenayanganRepository
import com.example.uas.repository.StudioRepository
import com.example.uas.repository.TiketRepository
import com.example.uas.service_api.FilmService
import com.example.uas.service_api.PenayanganService
import com.example.uas.service_api.StudioService
import com.example.uas.service_api.TiketService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val filmRepository: FilmRepository
    val penayanganRepository: PenayanganRepository
    val studioRepository: StudioRepository
    val tiketRepository: TiketRepository
}



/*class BioskopContainer : AppContainer {
    private val json = Json { ignoreUnknownKeys = true }

    // Base URL untuk API
    private val baseUrlFilm = "http://10.0.2.2:3000/api/film/"
    private val baseUrlPenayangan = "http://10.0.2.2:3000/api/penayangan/"
    private val baseUrlStudio = "http://10.0.2.2:3000/api/studio/"
    private val baseUrlTiket = "http://10.0.2.2:3000/api/tiket/"

    // Retrofit instance untuk Film
    private val retrofitFilm: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlFilm).build()

    private val filmService: FilmService by lazy { retrofitFilm.create(FilmService::class.java) }
    override val filmRepository: FilmRepository by lazy { NetworkFilmRepository(filmService) }

    // Retrofit instance untuk Penayangan
    private val retrofitPenayangan: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlPenayangan).build()

    private val penayanganService: PenayanganService by lazy { retrofitPenayangan.create(PenayanganService::class.java) }
    override val penayanganRepository: PenayanganRepository by lazy { NetworkPenayanganRepository(penayanganService) }

    // Retrofit instance untuk Studio
    private val retrofitStudio: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlStudio).build()

    private val studioService: StudioService by lazy { retrofitStudio.create(StudioService::class.java) }
    override val studioRepository: StudioRepository by lazy { NetworkStudioRepository(studioService) }

    // Retrofit instance untuk Tiket
    private val retrofitTiket: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrlTiket).build()

    private val tiketService: TiketService by lazy { retrofitTiket.create(TiketService::class.java) }
    override val tiketRepository: TiketRepository by lazy { NetworkTiketRepository(tiketService) }
}*/