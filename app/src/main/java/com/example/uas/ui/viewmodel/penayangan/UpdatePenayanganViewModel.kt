package com.example.uas.ui.viewmodel.penayangan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Penayangan
import com.example.uas.repository.PenayanganRepository
import kotlinx.coroutines.launch


//Berisi data input untuk fitur update penayangan
data class UpdateTayangUiEvent(
    val id_penayangan: String = " ",
    val id_film: String = " ",
    val id_studio: String = " ",
    val tanggal_penayangan: String = " ",
    val harga_tiket: String = " "
)