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



@Composable
fun TimePickerButton(
    insertFilmUiEvent: InsertFilmUiEvent,
    onValueChange: (InsertFilmUiEvent) -> Unit,
    enabled: Boolean
) {
    var isTimePickerVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val initialHour = calendar.get(Calendar.HOUR_OF_DAY)
    val initialMinute = calendar.get(Calendar.MINUTE)

    // Menampilkan TimePickerDialog ketika tombol ditekan
    if (isTimePickerVisible) {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                // Default to 0 seconds if seconds are not handled by the dialog
                val formattedTime = String.format("%02d:%02d:00", hourOfDay, minute)
                onValueChange(insertFilmUiEvent.copy(durasi = formattedTime)) // Update durasi
                isTimePickerVisible = false // Reset kondisi setelah memilih waktu
            },
            initialHour,
            initialMinute,
            true
        ).show()
    }

    // Tombol untuk memilih waktu, menampilkan durasi yang sudah dipilih
    Button(
        onClick = { isTimePickerVisible = true }, // Aktifkan dialog pemilih waktu
        enabled = enabled,
        modifier = Modifier.fillMaxWidth()
    ) {
        val buttonText = if (insertFilmUiEvent.durasi.isNotEmpty()) {
            insertFilmUiEvent.durasi // Display the selected duration in HH:mm:ss format
        } else {
            "Pilih Durasi" // Default text
        }
        Text(text = buttonText)
    }
}

// Fungsi validasi form
fun isFormValid(insertFilmUiEvent: InsertFilmUiEvent): Boolean {
    return insertFilmUiEvent.id_film.isNotBlank() &&
            insertFilmUiEvent.judul_film.isNotBlank() &&
            insertFilmUiEvent.deskripsi.isNotBlank() &&
            insertFilmUiEvent.genre.isNotBlank() &&
            insertFilmUiEvent.durasi.isNotBlank() &&
            insertFilmUiEvent.rating_usia.isNotBlank()
}
