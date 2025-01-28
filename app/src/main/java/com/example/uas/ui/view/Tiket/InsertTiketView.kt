package com.example.uas.ui.view.Tiket

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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.data.Listtayang
import com.example.uas.data.filmList
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.CustomWidget.DynamicSelectTextField
import com.example.uas.ui.navigasi.DestinasiInsertTiket
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.tiket.InsertTiketUiEvent
import com.example.uas.ui.viewmodel.tiket.InsertTiketUiState
import com.example.uas.ui.viewmodel.tiket.InsertTiketViewModel
import kotlinx.coroutines.launch




//Menyusun form untuk memasukkan data tiket.
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun EntryBody(
    insertTiketUiState: InsertTiketUiState,
    onTiketValueChange: (InsertTiketUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier
            .padding(12.dp)
    ) {
        FormTiket(
            insertTiketUiEvent = insertTiketUiState.insertTiketUiEvent,
            onValueChange = onTiketValueChange,
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .fillMaxWidth(),
            enabled = isFormValid(insertTiketUiState.insertTiketUiEvent)
        ) {
            Text(text = "Simpan")
        }
    }
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun FormTiket(
    insertTiketUiEvent: InsertTiketUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertTiketUiEvent) -> Unit = {},
    enabled: Boolean = true
){
    var chosenDropdowntayang by remember { mutableStateOf((" ")) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        // ID Tiket Field
        OutlinedTextField(
            value = insertTiketUiEvent.id_tiket,
            onValueChange = {
                println("ID Tiket: ${it}") // Debugging log
                onValueChange(insertTiketUiEvent.copy(id_tiket = it))
            },
            label = { Text("ID Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (insertTiketUiEvent.id_tiket.isBlank()) {
            Text(
                text = "ID Tiket tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // ID Penayangan Dropdown
        DynamicSelectTextField(
            selectedValue = chosenDropdowntayang,
            options = Listtayang.listtayang(),
            label = "Pilih ID Penayangan",
            onValueChangedEvent = { selectedTayang ->
                chosenDropdowntayang = selectedTayang
                onValueChange(insertTiketUiEvent.copy(id_penayangan = selectedTayang))
            },
            modifier = Modifier.fillMaxWidth()
        )
        if (insertTiketUiEvent.id_penayangan.isBlank()) {
            Text(
                text = "ID Penayangan tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Jumlah Tiket Field
        OutlinedTextField(
            value = insertTiketUiEvent.jumlah_tiket,
            onValueChange = { onValueChange(insertTiketUiEvent.copy(jumlah_tiket = it)) },
            label = { Text("Jumlah Tiket") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (insertTiketUiEvent.jumlah_tiket.isBlank()) {
            Text(
                text = "Jumlah Tiket tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Total Harga Field
        OutlinedTextField(
            value = insertTiketUiEvent.total_harga,
            onValueChange = { onValueChange(insertTiketUiEvent.copy(total_harga = it)) },
            label = { Text("Total Harga") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (insertTiketUiEvent.total_harga.isBlank()) {
            Text(
                text = "Total Harga tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Status Pembayaran
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Status Pembayaran")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val status_pembayaran = listOf("Lunas", "Belum Lunas")
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
        if (insertTiketUiEvent.status_pembayaran.isBlank()) {
            Text(
                text = "Status Pembayaran tidak boleh kosong",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
