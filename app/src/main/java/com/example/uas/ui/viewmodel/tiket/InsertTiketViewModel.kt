package com.example.uas.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Tiket
import com.example.uas.repository.TiketRepository
import kotlinx.coroutines.launch


//ntuk proses insert atau pembaruan data tiket di database
fun InsertTiketUiEvent.toTiket(): Tiket = Tiket (
    id_tiket = id_tiket,
    id_penayangan = id_penayangan,
    jumlah_tiket = jumlah_tiket,
    total_harga = total_harga,
    status_pembayaran = status_pembayaran
)

//untuk memperbarui data UI dengan informasi tiket
fun Tiket.toInsertTiketUiEvent(): InsertTiketUiEvent = InsertTiketUiEvent (
    id_tiket = id_tiket,
    id_penayangan = id_penayangan,
    jumlah_tiket = jumlah_tiket,
    total_harga = total_harga,
    status_pembayaran = status_pembayaran
)

//Menyimpan status UI terkait operasi penyisipan tiket
data class InsertTiketUiState(
    val insertTiketUiEvent: InsertTiketUiEvent = InsertTiketUiEvent()
)

//Menyimpan data tiket yang akan disisipkan atau diperbarui
data class InsertTiketUiEvent(
    val id_tiket: String = " ",
    val id_penayangan: String = " ",
    val jumlah_tiket: String = " ",
    val total_harga: String = " ",
    val status_pembayaran: String = " "
)