package com.example.uas.ui.view.Penayangan

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.data.filmList
import com.example.uas.data.studioList
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.CustomWidget.DynamicSelectTextField
import com.example.uas.ui.navigasi.DestinasiInsertPenayangan
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.penayangan.InsertPenayanganViewModel
import com.example.uas.ui.viewmodel.penayangan.InsertTayangUiEvent
import com.example.uas.ui.viewmodel.penayangan.InsertTayangUiState
import kotlinx.coroutines.launch
import java.util.Calendar

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertTayangView(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPenayanganViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiInsertPenayangan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){ innerPadding ->
        EntryTayang(
            insertTayangUiState = viewModel.uiState,
            onTayangValueChange = viewModel::updateInsertTayangState,
            onSaveClick = {
                // Validasi form
                if (isFormValid(viewModel.uiState.insertTayangUiEvent)) {
                    coroutineScope.launch {
                        viewModel.insertTayang()
                        navigateBack()
                    }
                } else {
                    // Tampilkan pesan error jika form tidak valid
                    println("Form tidak lengkap!")
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

// Fungsi untuk validasi form
fun isFormValid(insertTayangUiEvent: InsertTayangUiEvent): Boolean {
    return insertTayangUiEvent.id_penayangan.isNotBlank() &&
            insertTayangUiEvent.id_film.isNotBlank() &&
            insertTayangUiEvent.id_studio.isNotBlank() &&
            insertTayangUiEvent.tanggal_penayangan.isNotBlank() &&
            insertTayangUiEvent.harga_tiket.isNotBlank()
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun EntryTayang(
    insertTayangUiState: InsertTayangUiState,
    onTayangValueChange: (InsertTayangUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isFormValid = isFormValid(insertTayangUiState.insertTayangUiEvent)

    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormTayang(
            insertTayangUiEvent = insertTayangUiState.insertTayangUiEvent,
            onValueChange = onTayangValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = isFormValid, // Tombol hanya aktif jika form valid
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun FormTayang(
    insertTayangUiEvent: InsertTayangUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertTayangUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val datePicked = remember { mutableStateOf(insertTayangUiEvent.tanggal_penayangan) }
    var chosenDropdownfilm by remember { mutableStateOf((" ")) }
    var chosenDropdownstudio by remember { mutableStateOf((" ")) }

    // datepicker
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                datePicked.value = selectedDate
                onValueChange(insertTayangUiEvent.copy(tanggal_penayangan = selectedDate))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertTayangUiEvent.id_penayangan,
            onValueChange = { onValueChange(insertTayangUiEvent.copy(id_penayangan = it)) },
            label = { Text("ID Penayangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (insertTayangUiEvent.id_penayangan.isBlank()) {
            Text(
                text = "ID Penayangan tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Film Dropdown using DynamicSelectTextField
        DynamicSelectTextField(
            selectedValue = chosenDropdownfilm,
            options = filmList.listfilm(),
            label = "Pilih Judul Film",
            onValueChangedEvent = { selectedFilm ->
                chosenDropdownfilm = selectedFilm
                onValueChange(insertTayangUiEvent.copy(id_film = selectedFilm))
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (insertTayangUiEvent.id_film.isBlank()) {
            Text(
                text = "Judul Film tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Studio Dropdown using DynamicSelectTextField
        DynamicSelectTextField(
            selectedValue = chosenDropdownstudio,
            options = studioList.liststudio(),
            label = "Pilih Studio",
            onValueChangedEvent = { selectedStudio ->
                chosenDropdownstudio = selectedStudio
                onValueChange(insertTayangUiEvent.copy(id_studio = selectedStudio))
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (insertTayangUiEvent.id_studio.isBlank()) {
            Text(
                text = "Studio tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Date Picker Trigger - Using a Button
        Button(
            onClick = {
                datePickerDialog.show() // Open the DatePicker when the button is clicked
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (datePicked.value.isEmpty()) "Pick Date" else "Selected Date: ${datePicked.value}")
        }
        if (insertTayangUiEvent.tanggal_penayangan.isBlank()) {
            Text(
                text = "Tanggal Penayangan tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = insertTayangUiEvent.harga_tiket,
            onValueChange = { onValueChange(insertTayangUiEvent.copy(harga_tiket = it)) },
            label = { Text("Harga Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (insertTayangUiEvent.harga_tiket.isBlank()) {
            Text(
                text = "Harga Tiket tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
