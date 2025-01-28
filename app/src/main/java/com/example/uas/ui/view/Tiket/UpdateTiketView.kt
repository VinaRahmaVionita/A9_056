package com.example.uas.ui.view.Tiket

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.data.Listtayang
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.CustomWidget.DynamicSelectTextField
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.tiket.UpdateTiketUiEvent
import com.example.uas.ui.viewmodel.tiket.UpdateTiketViewModel
import kotlinx.coroutines.launch


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
//Menampilkan layar untuk memperbarui data tiket
fun UpdateTiketView(
    id_tiket: String,
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateTiketViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    //Menangani tampilan snackbar ketika ada pesan yang perlu ditampilkan.
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetsnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CostumeTopAppBar(
                title = "Update Tiket",
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
            FormTiket(
                insertTiketUiEvent = uiState.tiketEvent,
                onValueChange = viewModel::updateTiketState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.updateTiket()
                        onNavigate()
                    }
                }
            )
        }
    }
}

//Menampilkan form untuk mengedit data tiket
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTiket(
    insertTiketUiEvent: UpdateTiketUiEvent, //Data tiket yang digunakan untuk mengisi form
    modifier: Modifier = Modifier,
    onValueChange: (UpdateTiketUiEvent) -> Unit = {},
    enabled: Boolean = true,
    onSaveClick: () -> Unit
){
    var chosenDropdowntayang by remember { mutableStateOf((" ")) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        val status_pembayaran = listOf("Lunas","Belum Lunas")
        OutlinedTextField(
            value = insertTiketUiEvent.id_tiket,
            onValueChange = { onValueChange(insertTiketUiEvent.copy(id_tiket = it)) },
            label = { Text("ID Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            singleLine = true
        )
        // Film Dropdown using DynamicSelectTextField
        DynamicSelectTextField(
            selectedValue = chosenDropdowntayang,
            options = Listtayang.listtayang(),
            label = "Pilih ID Penayangan",
            onValueChangedEvent = { selectedTayang -> chosenDropdowntayang = selectedTayang
                onValueChange(insertTiketUiEvent.copy(id_penayangan = selectedTayang))
            },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = insertTiketUiEvent.jumlah_tiket,
            onValueChange = { onValueChange(insertTiketUiEvent.copy(jumlah_tiket = it)) },
            label = { Text("Jumlah Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertTiketUiEvent.total_harga,
            onValueChange = { onValueChange(insertTiketUiEvent.copy(total_harga = it)) },
            label = { Text("Total Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Status Pembayaran")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            status_pembayaran.forEach { bayar ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    RadioButton(
                        selected = insertTiketUiEvent.status_pembayaran == bayar,
                        onClick = {
                            onValueChange(insertTiketUiEvent.copy(status_pembayaran = bayar))
                        },
                    )
                    Text(
                        text = bayar,
                    )
                }
            }
        }
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