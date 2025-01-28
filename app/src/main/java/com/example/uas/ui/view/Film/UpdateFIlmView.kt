package com.example.uas.ui.view.Film

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.viewmodel.Film.UpdateFilmUiEvent
import com.example.uas.ui.viewmodel.Film.UpdateFilmViewModel
import com.example.uas.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun UpdateFilmView(
    id_film: String,
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateFilmViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CostumeTopAppBar(
                title = "Update Film",
                canNavigateBack = true,
                navigateUp = onBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            FormFilm(
                insertFilmUiEvent = uiState.filmEvent,
                onValueChange = viewModel::updateFilmState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.updateFilm()
                        onNavigate()
                    }
                }
            )
        }
    }
}

@Composable
fun FormFilm(
    insertFilmUiEvent: UpdateFilmUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateFilmUiEvent) -> Unit = {},
    enabled: Boolean = true,
    onSaveClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val rating_usia = listOf("R13+", "SU", "R17+")

        // ID Film TextField (disabled)
        OutlinedTextField(
            value = insertFilmUiEvent.id_film,
            onValueChange = { onValueChange(insertFilmUiEvent.copy(id_film = it)) },
            label = { Text("ID Film") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            singleLine = true
        )

        // Judul Film TextField
        OutlinedTextField(
            value = insertFilmUiEvent.judul_film,
            onValueChange = { onValueChange(insertFilmUiEvent.copy(judul_film = it)) },
            label = { Text("Judul Film") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Durasi sebagai TimePicker
        TimePickerField(
            insertFilmUiEvent = insertFilmUiEvent,
            onValueChange = onValueChange,
            enabled = false
        )

        // Deskripsi Film TextField
        OutlinedTextField(
            value = insertFilmUiEvent.deskripsi,
            onValueChange = { onValueChange(insertFilmUiEvent.copy(deskripsi = it)) },
            label = { Text("Deskripsi Film") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        // Genre Film TextField
        OutlinedTextField(
            value = insertFilmUiEvent.genre,
            onValueChange = { onValueChange(insertFilmUiEvent.copy(genre = it)) },
            label = { Text("Genre Film") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Rating Usia RadioButton
        Text(text = "Rating Usia")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            rating_usia.forEach { usia ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = insertFilmUiEvent.rating_usia == usia,
                        onClick = {
                            onValueChange(insertFilmUiEvent.copy(rating_usia = usia))
                        },
                    )
                    Text(
                        text = usia,
                    )
                }
            }
        }

        // Button to Save/Update the Film
        Button(
            onClick = { onSaveClick() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0C50AD), // Warna biru tua
                contentColor = Color.White // Warna teks tombol menjadi putih
            ),
        ) {
            Text(text = "Update")
        }
    }
}

@Composable
fun TimePickerField(
    insertFilmUiEvent: UpdateFilmUiEvent,
    onValueChange: (UpdateFilmUiEvent) -> Unit,
    enabled: Boolean
) {
    var isTimePickerVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val initialHour = calendar.get(Calendar.HOUR_OF_DAY)
    val initialMinute = calendar.get(Calendar.MINUTE)

    // Create TimePickerDialog
    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                val formattedTime = String.format("%02d:%02d", hourOfDay, minute)
                onValueChange(insertFilmUiEvent.copy(durasi = formattedTime)) // Update the value of durasi
            },
            initialHour,
            initialMinute,
            true // 24-hour format
        )
    }

    // OutlinedTextField acting as a time picker field
    OutlinedTextField(
        value = insertFilmUiEvent.durasi,
        onValueChange = {},
        label = { Text("Durasi") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled) {
                isTimePickerVisible = true
            },
        enabled = enabled,
        singleLine = true,
        readOnly = true // Prevent manual editing
    )

    // Show TimePickerDialog when triggered
    LaunchedEffect(isTimePickerVisible) {
        if (isTimePickerVisible) {
            timePickerDialog.show() // Show the TimePickerDialog
            isTimePickerVisible = false // Hide the dialog after showing
        }
    }
}