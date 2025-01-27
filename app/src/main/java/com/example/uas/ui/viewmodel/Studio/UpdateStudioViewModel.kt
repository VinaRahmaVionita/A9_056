package com.example.uas.ui.viewmodel.Studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Studio
import com.example.uas.repository.StudioRepository
import kotlinx.coroutines.launch

//Mengelola state (data dan tampilan) untuk tampilan update studio
class UpdateStudioViewModel(
    private val repository: StudioRepository, //Objek untuk mengakses data studio dari database
    savedStateHandle: SavedStateHandle //Menyimpan data yang dibutuhkan, seperti id_studio
): ViewModel() {
    var uiState by mutableStateOf(UpdateStudioUiState())
        private set

    private val _id_studio: String = checkNotNull(savedStateHandle["id_studio"])

    init {
        getStudioDetail()
    }
    //Mengambil data studio dari database berdasarkan ID
    private fun getStudioDetail() {
        viewModelScope.launch {
            try {
                val studio = repository.getStudioById(_id_studio)
                uiState = UpdateStudioUiState(studioEvent = studio.toUpdateStudioUiEvent())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    //Memperbarui state aplikasi (UI) ketika ada perubahan data di form
    fun updateStudioState(studioEvent: UpdateStudioUiEvent) {
        uiState = uiState.copy(studioEvent = studioEvent)
    }
    //Menyimpan perubahan data studio ke dalam database
    fun updateStudio() {
        val currentEvent = uiState.studioEvent
        viewModelScope.launch {
            try {
                repository.updateStudio(currentEvent.id_studio, currentEvent.toStudioEntity())
                uiState = uiState.copy(
                    snackBarMessage = "Data berhasil diupdate",
                    studioEvent = UpdateStudioUiEvent()
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    snackBarMessage = "Data gagal diupdate"
                )
            }
        }
    }

    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}
//Mengubah objek Studio (data dari database) menjadi UpdateStudioUiEvent (data untuk UI)
fun Studio.toUpdateStudioUiEvent(): UpdateStudioUiEvent = UpdateStudioUiEvent(
    id_studio = id_studio,
    nama_studio = nama_studio,
    kapasitas = kapasitas
)
//Mengubah UpdateStudioUiEvent (data UI) menjadi objek Studio (data untuk database)
fun UpdateStudioUiEvent.toStudioEntity(): Studio = Studio(
    id_studio = id_studio,
    nama_studio = nama_studio,
    kapasitas = kapasitas
)
//menyimpan status ui
data class UpdateStudioUiState(
    val studioEvent: UpdateStudioUiEvent = UpdateStudioUiEvent(),
    val snackBarMessage: String? = null
)
//Data yang mewakili input pengguna, seperti id_studio, nama_studio, dan kapasitas
data class UpdateStudioUiEvent(
    val id_studio: String = "",
    val nama_studio: String = "",
    val kapasitas: String = ""
)