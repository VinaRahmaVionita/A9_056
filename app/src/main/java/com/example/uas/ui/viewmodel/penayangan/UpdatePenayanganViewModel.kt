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

//Mengelola state dan logika untuk fitur update penayangan
class UpdatePenayanganViewModel(
    private val repository: PenayanganRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(UpdateTayangUiState())
        private set
    private val _id_penayangan: String = checkNotNull(savedStateHandle["id_penayangan"])

    init {
        getTayangDetail()
    }
    //Mengambil data penayangan dari repository
    private fun getTayangDetail() {
        viewModelScope.launch {
            try {
                val penayangan = repository.getPenayanganById(_id_penayangan)
                uiState = UpdateTayangUiState(penayanganEvent = penayangan.toUpdateTayangUiEvent())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    //Memperbarui state UI dengan data baru dari pengguna
    fun updateTayangState(penayanganEvent: UpdateTayangUiEvent) {
        uiState = uiState.copy(penayanganEvent = penayanganEvent)
    }
    //Memperbarui data penayangan di repository menggunakan data dari uiState
    fun updateTayang() {
        val currentEvent = uiState.penayanganEvent
        viewModelScope.launch {
            try {
                repository.updatePenayangan(currentEvent.id_penayangan, currentEvent.toTayangEntity())
                uiState = uiState.copy(
                    snackBarMessage = "Data berhasil diupdate",
                    penayanganEvent = UpdateTayangUiEvent()
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    snackBarMessage = "Data gagal diupdate"
                )
            }
        }
    }
    //Menghapus pesan snackbar setelah selesai ditampilkan
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}
//Mengubah objek Penayangan menjadi model untuk UI
//mempermudah pengisian data dari database ke UI
fun Penayangan.toUpdateTayangUiEvent(): UpdateTayangUiEvent = UpdateTayangUiEvent(
    id_penayangan = id_penayangan,
    id_film = id_film,
    id_studio = id_studio,
    tanggal_penayangan = tanggal_penayangan,
    harga_tiket = harga_tiket
)
//Mengubah objek UpdateTayangUiEvent menjadi Penayangan (database)
fun UpdateTayangUiEvent.toTayangEntity(): Penayangan = Penayangan(
    id_penayangan = id_penayangan,
    id_film = id_film,
    id_studio = id_studio,
    tanggal_penayangan = tanggal_penayangan,
    harga_tiket = harga_tiket
)
//Menyimpan state untuk UI
data class UpdateTayangUiState(
    val penayanganEvent: UpdateTayangUiEvent = UpdateTayangUiEvent(),
    val snackBarMessage: String? = null
)
//Berisi data input untuk fitur update penayangan
data class UpdateTayangUiEvent(
    val id_penayangan: String = " ",
    val id_film: String = " ",
    val id_studio: String = " ",
    val tanggal_penayangan: String = " ",
    val harga_tiket: String = " "
)