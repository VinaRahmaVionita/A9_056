package com.example.uas.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Tiket
import com.example.uas.repository.TiketRepository
import com.example.uas.ui.navigasi.DestinasiDetailTiket
import kotlinx.coroutines.launch


//Data class yang menyimpan status UI terkait detail tiket.
data class DetailTiketUiState(
    val detailTiketUiEvent: InsertTiketUiEvent = InsertTiketUiEvent(), //Data class yang menyimpan status UI terkait detail tiket
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailTiketUiEvent == InsertTiketUiEvent()
    val isUiEventNotEmpty: Boolean
        get() = detailTiketUiEvent != InsertTiketUiEvent()
}
//untuk mengonversi data tiket yang diambil dari repository
fun Tiket.toDetailTiketUiEvent(): InsertTiketUiEvent{
    return InsertTiketUiEvent(
        id_tiket = id_tiket,
        id_penayangan = id_penayangan,
        jumlah_tiket = jumlah_tiket,
        total_harga = total_harga,
        status_pembayaran = status_pembayaran
    )
}