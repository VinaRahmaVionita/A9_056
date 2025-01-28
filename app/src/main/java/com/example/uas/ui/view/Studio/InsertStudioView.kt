package com.example.uas.ui.view.Studio

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.navigasi.DestinasiInsertStudio
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.Studio.InsertStudioUiEvent
import com.example.uas.ui.viewmodel.Studio.InsertStudioUiState
import com.example.uas.ui.viewmodel.Studio.InsertStudioViewModel
import kotlinx.coroutines.launch



@Composable
fun FormStudio(
    insertStudioUiEvent: InsertStudioUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertStudioUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ID Studio Field
        OutlinedTextField(
            value = insertStudioUiEvent.id_studio,
            onValueChange = { onValueChange(insertStudioUiEvent.copy(id_studio = it)) },
            label = { Text("ID Studio") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (insertStudioUiEvent.id_studio.isBlank()) {
            Text(
                text = "ID Studio tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Nama Studio Field
        OutlinedTextField(
            value = insertStudioUiEvent.nama_studio,
            onValueChange = { onValueChange(insertStudioUiEvent.copy(nama_studio = it)) },
            label = { Text("Nama Studio") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (insertStudioUiEvent.nama_studio.isBlank()) {
            Text(
                text = "Nama Studio tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Kapasitas Field
        OutlinedTextField(
            value = insertStudioUiEvent.kapasitas,
            onValueChange = { onValueChange(insertStudioUiEvent.copy(kapasitas = it)) },
            label = { Text("Kapasitas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (insertStudioUiEvent.kapasitas.isBlank()) {
            Text(
                text = "Kapasitas tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
