package com.example.uas.ui.viewmodel.Studio

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Studio
import com.example.uas.repository.StudioRepository
import kotlinx.coroutines.launch
import java.io.IOException
//Mengelompokkan berbagai status UI menjadi satu
sealed class HomeStudioUiState {
    data class Success(val studio: List<Studio>) : HomeStudioUiState() //Data berhasil dimuat. Berisi daftar studio (List<Studio>)
    object Error : HomeStudioUiState()
    object Loading : HomeStudioUiState()
}
//Mengelola state UI untuk beranda studio.
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HomeStudioViewModel (private val std: StudioRepository) : ViewModel(){
    //State utama yang merepresentasikan status UI
    var stdUiState: HomeStudioUiState by mutableStateOf(HomeStudioUiState.Loading)
        private set

    init {
        getStudio()
    }
    //Mengambil daftar studio dari database
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getStudio() {
        viewModelScope.launch {
            stdUiState = HomeStudioUiState.Loading
            stdUiState = try {
                HomeStudioUiState.Success(std.getStudio())
            } catch (e: IOException){
                HomeStudioUiState.Error
            } catch (e: HttpException) {
                HomeStudioUiState.Error
            }
        }
    }
    //Menghapus data studio berdasarkan id_studio
    fun deleteStudio(id_studio: String){
        viewModelScope.launch {
            try {
                std.deleteStudio(id_studio)
            }catch (e: IOException){
                HomeStudioUiState.Error
            }catch (e: HttpException){
                HomeStudioUiState.Error
            }
        }
    }
}