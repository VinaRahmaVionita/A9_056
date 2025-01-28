package com.example.uas.ui.view.Penayangan

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.model.Penayangan
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.navigasi.DestinasiDetailPenayangan
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.penayangan.DetailPenayanganViewModel
import com.example.uas.ui.viewmodel.penayangan.DetailTayangUiState
import com.example.uas.ui.viewmodel.penayangan.toTayang

@OptIn(ExperimentalMaterial3Api::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun DetailTayangView(
    modifier: Modifier = Modifier,
    NavigateBack: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit = { },
    onTiketClick: () -> Unit, // New parameter for the tiket click
    viewModel: DetailPenayanganViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailPenayangan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = NavigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onEditClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp),
                containerColor = Color(0xFF92BCEA)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Penayangan"
                )
            }
        }
    ) { innerPadding ->
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        BodyDetailtayang(
            detailTayangUiState = viewModel.detailTayangUiState,
            modifier = Modifier.padding(innerPadding),
            onDeleteClick = {
                deleteConfirmationRequired = true
            },
            onTiketClick = onTiketClick // Pass the onTiketClick function here
        )

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    viewModel.deleteTayang()
                    onDeleteClick()
                    deleteConfirmationRequired = false
                },
                onDeleteCancel = {
                    deleteConfirmationRequired = false
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@Composable
fun BodyDetailtayang(
    modifier: Modifier = Modifier,
    detailTayangUiState: DetailTayangUiState,
    onDeleteClick: () -> Unit,
    onTiketClick: () -> Unit
) {
    when {
        detailTayangUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailTayangUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailTayangUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailTayangUiState.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailTayang(
                    penayangan = detailTayangUiState.detailTayangUiEvent.toTayang(),
                    modifier = modifier
                )

                Spacer(modifier = Modifier.padding(8.dp))

                // Button for deleting
                Button(
                    onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3A66D9), // Warna biru muda
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }

                // Spacer for separation between the buttons
                Spacer(modifier = Modifier.padding(8.dp))

                // New button for tiket
                Button(
                    onClick = onTiketClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3A66D9), // Warna biru muda
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(text = "Beli Tiket")
                }
            }
        }
    }
}


@Composable
fun ItemDetailTayang(
    modifier: Modifier = Modifier,
    penayangan: Penayangan,
){
    Card(
        modifier = modifier.fillMaxWidth().padding(top = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column(
            modifier = Modifier
                .background(Color(0xFFB3D8F5))
                .padding(16.dp)
        ) {
            ComponentDetailTayang(judul = "ID Penayangan", isinya = penayangan.id_penayangan)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailTayang(judul = "Film", isinya = penayangan.id_film)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailTayang(judul = "Studio", isinya = penayangan.id_studio)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailTayang(judul = "Tanggal Penayangan", isinya = penayangan.tanggal_penayangan)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailTayang(judul = "Penayangan", isinya = penayangan.harga_tiket)
        }
    }
}

@Composable
fun ComponentDetailTayang(
    modifier: Modifier = Modifier,
    judul:String,
    isinya:String
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = { /*Do nothing*/ },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        dismissButton = {
            TextButton(onClick = { onDeleteCancel() }) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDeleteConfirm() }) {
                Text(text = "Yes")
            }
        }
    )
}