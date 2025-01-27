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

//mengelola status UI untuk memperbarui tiket.
class UpdateTiketViewModel (
    private val repository: TiketRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var uiState by mutableStateOf(UpdateTiketUiState())
        private set
    private val _id_tiket: String = checkNotNull(savedStateHandle["id_tiket"])

    init {
        getTiketDetail()
    }

    private fun getTiketDetail(){
        viewModelScope.launch {
            try {
                val tiket = repository.getTiketById(_id_tiket)
                uiState = UpdateTiketUiState(tiketEvent = tiket.toUpdateTiketUiEvent())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun updateTiketState(tiketEvent: UpdateTiketUiEvent){
        uiState = uiState.copy(tiketEvent = tiketEvent)
    }

    fun updateTiket() {
        val currentEvent = uiState.tiketEvent
        viewModelScope.launch {
            try {
                repository.updateTiket(currentEvent.id_tiket, currentEvent.toTiketEntity())
                uiState = uiState.copy(
                    snackBarMessage = "Data berhasil diupdate",
                    tiketEvent = UpdateTiketUiEvent()
                )
            } catch (e: Exception){
                uiState = uiState.copy(
                    snackBarMessage = "Data gagal diupdate"
                )
            }
        }
    }
    //menghapus pesan snackbar dari status UI.
    fun resetsnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

//mengubah objek Tiket menjadi objek UpdateTiketUiEvent.
fun Tiket.toUpdateTiketUiEvent(): UpdateTiketUiEvent = UpdateTiketUiEvent(
    id_tiket = id_tiket,
    id_penayangan = id_penayangan,
    jumlah_tiket = jumlah_tiket,
    total_harga = total_harga,
    status_pembayaran = status_pembayaran
)

//mengubah objek UpdateTiketUiEvent kembali menjadi objek Tiket
fun UpdateTiketUiEvent.toTiketEntity(): Tiket = Tiket(
    id_tiket = id_tiket,
    id_penayangan = id_penayangan,
    jumlah_tiket = jumlah_tiket,
    total_harga = total_harga,
    status_pembayaran = status_pembayaran
)

//data class yang merepresentasikan status UI
data class UpdateTiketUiState(
    val tiketEvent: UpdateTiketUiEvent = UpdateTiketUiEvent(),
    val snackBarMessage: String? = null
)

//state untuk memperbarui data tiket.
data class UpdateTiketUiEvent(
    val id_tiket: String = " ",
    val id_penayangan: String = " ",
    val jumlah_tiket: String = " ",
    val total_harga: String = " ",
    val status_pembayaran: String = " "
)