package com.example.uas.ui.viewmodel.penayangan

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Penayangan
import com.example.uas.repository.PenayanganRepository
import kotlinx.coroutines.launch
import java.io.IOException

//merepresentasikan status UI pada halaman daftar penayangan
sealed class HomePenayanganUiState {
    data class Success(val penayangan: List<Penayangan>) : HomePenayanganUiState()
    object Error : HomePenayanganUiState()
    object Loading : HomePenayanganUiState()
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomePenayanganViewModel (private val tayang: PenayanganRepository) : ViewModel() {
    //menyimpan status UI (loading, success, atau error)
    var tayangUiState: HomePenayanganUiState by mutableStateOf(HomePenayanganUiState.Loading)
        private set

    init {
        getTayang()
    }
    //Fungsi untuk memuat data penayangan dari repository
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getTayang(){
        viewModelScope.launch {
            tayangUiState = HomePenayanganUiState.Loading
            tayangUiState = try {
                HomePenayanganUiState.Success(tayang.getPenayangan())
            } catch (e: IOException) {
                HomePenayanganUiState.Error
            } catch (e: HttpException) {
                HomePenayanganUiState.Error
            }
        }
    }
    //menghapus data penayangan berdasarkan ID
    fun deleteTayang(id_penayangan: String){
        viewModelScope.launch {
            try {
                tayang.deletePenayangan(id_penayangan)
            } catch (e:IOException){
                HomePenayanganUiState.Error
            } catch (e: HttpException){
                HomePenayanganUiState.Error
            }
        }
    }
}