package com.example.uas.ui.viewmodel.Studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Studio
import com.example.uas.repository.StudioRepository
import kotlinx.coroutines.launch

//Mengelola logika aplikasi untuk operasi penambahan dan pembaruan data studio
class InsertStudioViewModel(private val std: StudioRepository): ViewModel() {
    var uiState by mutableStateOf(InsertStudioUiState())
        private set
    //Memperbarui state dengan data input pengguna
    fun updateInsertStdState(insertStudioUiEvent: InsertStudioUiEvent){
        uiState = InsertStudioUiState(insertStudioUiEvent = insertStudioUiEvent)
    }
    //Menyimpan data baru ke database
    suspend fun InsertStd(){
        viewModelScope.launch {
            try {
                std.insertStudio(uiState.insertStudioUiEvent.toStd())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
    //Memperbarui data studio yang sudah ada di database berdasarkan id_studio
    suspend fun updateStd(id_studio: String){
        viewModelScope.launch {
            try {
                std.updateStudio(id_studio, uiState.insertStudioUiEvent.toStd())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
//Mengubah objek Studio (data dari database) menjadi state UI
fun Studio.toUiStateStd(): InsertStudioUiState = InsertStudioUiState(
    insertStudioUiEvent = toInsertStudioUiEvent()
)
//Mengubah state UI (InsertStudioUiEvent) menjadi objek Studio untuk disimpan ke database
fun InsertStudioUiEvent.toStd(): Studio = Studio(
    id_studio = id_studio,
    nama_studio = nama_studio,
    kapasitas = kapasitas
)
//Mengubah objek Studio menjadi event UI (InsertStudioUiEvent)
fun Studio.toInsertStudioUiEvent(): InsertStudioUiEvent = InsertStudioUiEvent(
    id_studio = id_studio,
    nama_studio = nama_studio,
    kapasitas = kapasitas
)
//Menyimpan state layar (UI), khususnya data input yang sedang diisi pengguna
data class InsertStudioUiState(
    val insertStudioUiEvent: InsertStudioUiEvent = InsertStudioUiEvent()
)
//Mewakili data input pengguna
data class InsertStudioUiEvent(
    val id_studio: String = "",
    val nama_studio: String = "",
    val kapasitas: String = ""
)