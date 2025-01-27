package com.example.uas.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Tiket
import com.example.uas.repository.TiketRepository
import kotlinx.coroutines.launch


//state untuk memperbarui data tiket.
data class UpdateTiketUiEvent(
    val id_tiket: String = " ",
    val id_penayangan: String = " ",
    val jumlah_tiket: String = " ",
    val total_harga: String = " ",
    val status_pembayaran: String = " "
)