package com.example.uas.ui.view.Film

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.navigasi.DestinasiInsertFilm
import com.example.uas.ui.viewmodel.Film.InsertFilmUiEvent
import com.example.uas.ui.viewmodel.Film.InsertFilmUiState
import com.example.uas.ui.viewmodel.Film.InsertFilmViewModel
import com.example.uas.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch
import java.util.Calendar



// Fungsi validasi form
fun isFormValid(insertFilmUiEvent: InsertFilmUiEvent): Boolean {
    return insertFilmUiEvent.id_film.isNotBlank() &&
            insertFilmUiEvent.judul_film.isNotBlank() &&
            insertFilmUiEvent.deskripsi.isNotBlank() &&
            insertFilmUiEvent.genre.isNotBlank() &&
            insertFilmUiEvent.durasi.isNotBlank() &&
            insertFilmUiEvent.rating_usia.isNotBlank()
}
