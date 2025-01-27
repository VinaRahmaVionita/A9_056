package com.example.uas.ui.viewmodel.tiket

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Tiket
import com.example.uas.repository.TiketRepository
import kotlinx.coroutines.launch
import java.io.IOException

//Mengelola status UI terkait dengan daftar tiket di halaman utama
sealed class HomeTiketUiState {
    //Menyimpan data tiket dalam bentuk list
    data class Success(val tiket: List<Tiket>) : HomeTiketUiState()
    object Error : HomeTiketUiState()
    object Loading : HomeTiketUiState()
}
//Mengelola logika dan status UI terkait tiket
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeTiketViewModel (private val tiket: TiketRepository) : ViewModel() {
    var tiketUiState: HomeTiketUiState by mutableStateOf(HomeTiketUiState.Loading)
        private set
    init {
        getTiket()
    }
    //Mengambil daftar tiket dari repository
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getTiket() {
        viewModelScope.launch {
            tiketUiState = HomeTiketUiState.Loading
            tiketUiState = try {
                HomeTiketUiState.Success(tiket.getTiket())
            } catch (e: IOException){
                HomeTiketUiState.Error
            } catch (e: HttpException){
                HomeTiketUiState.Error
            }
        }
    }
    // Menghapus tiket berdasarkan id_tiket dan menangani kesalahan jika ada
    fun deleteTiket(id_tiket: String){
        viewModelScope.launch {
            try {
                tiket.deleteTiket(id_tiket)
            } catch (e: IOException){
                HomeTiketUiState.Error
            } catch (e: HttpException){
                HomeTiketUiState.Error
            }
        }
    }
}