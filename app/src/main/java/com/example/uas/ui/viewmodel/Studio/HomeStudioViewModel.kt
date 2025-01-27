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
