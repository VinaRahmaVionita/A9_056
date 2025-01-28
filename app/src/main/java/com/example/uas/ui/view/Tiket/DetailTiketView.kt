package com.example.uas.ui.view.Tiket

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
import com.example.uas.model.Tiket
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.navigasi.DestinasiDetailTiket
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.tiket.DetailTiketUiState
import com.example.uas.ui.viewmodel.tiket.DetailTiketViewModel
import com.example.uas.ui.viewmodel.tiket.toTiket

//tampilan untuk detail dari tiket yang dipilih
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTiketView(
    modifier: Modifier = Modifier,
    NavigateBack: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit = { },
    viewModel: DetailTiketViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailTiket.titleRes,
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
                    contentDescription = "Edit Tiket"
                )
            }
        }
    ) { innerPadding ->
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

        BodyDetailTiket (
            detailTiketUiState = viewModel.detailTiketUiState,
            modifier = Modifier.padding(innerPadding),
            onDeleteClick = {
                deleteConfirmationRequired = true
            }
        )

        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    viewModel.deleteTiket()
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

//Menampilkan konten detail tiket, memeriksa status UI (loading, error, atau data berhasil dimuat)
@Composable
fun BodyDetailTiket(
    modifier: Modifier = Modifier,
    detailTiketUiState: DetailTiketUiState,
    onDeleteClick: () -> Unit
) {
    when {
        detailTiketUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        detailTiketUiState.isError -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = detailTiketUiState.errorMessage,
                    color = Color.Red
                )
            }
        }
        detailTiketUiState.isUiEventNotEmpty -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailTiket(
                    tiket = detailTiketUiState.detailTiketUiEvent.toTiket(),
                    modifier = modifier
                )

                Spacer(modifier = Modifier.padding(8.dp))
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
            }
        }
    }
}
//Menampilkan informasi detail tentang tiket yang dipilih
@Composable
fun ItemDetailTiket(
    modifier: Modifier = Modifier,
    tiket: Tiket,
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
            ComponentDetailTiket(judul = "ID Tiket", isinya = tiket.id_tiket)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailTiket(judul = "ID Penayangan", isinya = tiket.id_penayangan)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailTiket(judul = "Jumlah Tiket", isinya = tiket.jumlah_tiket)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailTiket(judul = "Total Harga", isinya = tiket.total_harga)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailTiket(judul = "Kelas", isinya = tiket.status_pembayaran)
        }
    }
}
//Menampilkan label dan nilai untuk setiap informasi detail tiket
@Composable
fun ComponentDetailTiket(
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

//Menampilkan dialog konfirmasi sebelum tiket dihapus
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