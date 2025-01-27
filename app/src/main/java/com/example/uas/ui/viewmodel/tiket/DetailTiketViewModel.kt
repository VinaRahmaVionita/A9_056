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

//mengelola status dan logika terkait dengan detail tiket
class DetailTiketViewModel (
    savedStateHandle: SavedStateHandle,
    private val tiketRepository: TiketRepository
): ViewModel(){
    private val id_tiket: String = checkNotNull(savedStateHandle[DestinasiDetailTiket.id_tiket])

    var detailTiketUiState: DetailTiketUiState by mutableStateOf(DetailTiketUiState())
        private set
    init {
        getTiketById()
    }
    //mengambil detail tiket berdasarkan id_tiket menggunakan repository
    private fun getTiketById(){
        viewModelScope.launch {
            detailTiketUiState = DetailTiketUiState(isLoading = true)
            try {
                val result = tiketRepository.getTiketById(id_tiket)
                detailTiketUiState = DetailTiketUiState(
                    detailTiketUiEvent = result.toDetailTiketUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception){
                detailTiketUiState = DetailTiketUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }
    //untuk menghapus tiket berdasarkan id_tiket
    fun deleteTiket() {
        viewModelScope.launch {
            detailTiketUiState = DetailTiketUiState(isLoading = true)
            try {
                tiketRepository.deleteTiket(id_tiket)
                detailTiketUiState = DetailTiketUiState(isLoading = false)
            } catch (e: Exception){
                detailTiketUiState = DetailTiketUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}
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