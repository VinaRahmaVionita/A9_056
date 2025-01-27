package com.example.uas.ui.viewmodel.Studio

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Studio
import com.example.uas.repository.StudioRepository
import com.example.uas.ui.navigasi.DestinasiDetailStudio
import kotlinx.coroutines.launch

class DetailStudioViewModel(
    savedStateHandle: SavedStateHandle,//Menyimpan data id_studio yang diteruskan dari layar sebelumnya
    private val studioRepository: StudioRepository //Repository untuk operasi database.
) : ViewModel() {
    private val id_studio: String = checkNotNull(savedStateHandle[DestinasiDetailStudio.id_studio])

    var detailStudioUiState: DetailStudioUiState by mutableStateOf(DetailStudioUiState())
        private set

    init {
        getStudioById()
    }
    //Mengambil data detail studio berdasarkan id_studio
    private fun getStudioById() {
        viewModelScope.launch {
            detailStudioUiState = DetailStudioUiState(isLoading = true)
            try {
                val result = studioRepository.getStudioById(id_studio)
                detailStudioUiState = DetailStudioUiState(
                    detailStudioUiEvent = result.toDetailStudioUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailStudioUiState = DetailStudioUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }
    //Menghapus studio dari database berdasarkan id_studio
    fun deleteStd() {
        viewModelScope.launch {
            detailStudioUiState = DetailStudioUiState(isLoading = true)
            try {
                studioRepository.deleteStudio(id_studio)
                detailStudioUiState = DetailStudioUiState(isLoading = false)
            } catch (e: Exception) {
                detailStudioUiState = DetailStudioUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}
//memuat informasi tentang kondisi data
data class DetailStudioUiState(
    val detailStudioUiEvent: InsertStudioUiEvent = InsertStudioUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailStudioUiEvent == InsertStudioUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailStudioUiEvent != InsertStudioUiEvent()
}
//Mengubah objek Studio menjadi InsertStudioUiEvent untuk ditampilkan pada UI
fun Studio.toDetailStudioUiEvent(): InsertStudioUiEvent{
    return InsertStudioUiEvent(
        id_studio = id_studio,
        nama_studio = nama_studio,
        kapasitas = kapasitas
    )
}