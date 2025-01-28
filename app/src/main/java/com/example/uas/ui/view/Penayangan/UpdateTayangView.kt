package com.example.uas.ui.view.Penayangan

import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.data.filmList
import com.example.uas.data.studioList
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.CustomWidget.DynamicSelectTextField
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.penayangan.UpdatePenayanganViewModel
import com.example.uas.ui.viewmodel.penayangan.UpdateTayangUiEvent
import kotlinx.coroutines.launch



@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun FormTayang(
    insertTayangUiEvent: UpdateTayangUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateTayangUiEvent) -> Unit = {},
    enabled: Boolean = true,
    onSaveClick: () -> Unit
){
    var chosenDropdownfilm by remember { mutableStateOf((" ")) }
    var chosenDropdownstudio by remember { mutableStateOf((" ")) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertTayangUiEvent.id_penayangan,
            onValueChange = { onValueChange(insertTayangUiEvent.copy(id_penayangan = it)) },
            label = { Text("ID Penayangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            singleLine = true
        )
        // Film Dropdown using DynamicSelectTextField
        DynamicSelectTextField(
            selectedValue = chosenDropdownfilm,
            options = filmList.listfilm(),
            label = "Pilih Judul Film",
            onValueChangedEvent = { selectedFilm -> chosenDropdownfilm = selectedFilm
                onValueChange(insertTayangUiEvent.copy(id_film = selectedFilm))
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Studio Dropdown using DynamicSelectTextField
        DynamicSelectTextField(
            selectedValue = chosenDropdownstudio,
            options = studioList.liststudio(),
            label = "Pilih Studio",
            onValueChangedEvent = { selectedStudio -> chosenDropdownstudio = selectedStudio
                onValueChange(insertTayangUiEvent.copy(id_studio = selectedStudio))
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = insertTayangUiEvent.tanggal_penayangan,
            onValueChange = { onValueChange(insertTayangUiEvent.copy(tanggal_penayangan = it)) },
            label = { Text("Tanggal Penayangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertTayangUiEvent.harga_tiket,
            onValueChange = { onValueChange(insertTayangUiEvent.copy(harga_tiket = it)) },
            label = { Text("Harga Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (
            enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.
                padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.
            padding(12.dp)
        )
        Button(
            onClick = { onSaveClick() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Update")
        }
    }
}