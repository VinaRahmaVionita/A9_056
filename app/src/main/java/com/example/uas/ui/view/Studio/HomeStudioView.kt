package com.example.uas.ui.view.Studio

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.R
import com.example.uas.model.Studio
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.navigasi.DestinasiHomeStudio
import com.example.uas.ui.view.Film.OnLoading
import com.example.uas.ui.viewmodel.PenyediaViewModel
import com.example.uas.ui.viewmodel.Studio.HomeStudioUiState
import com.example.uas.ui.viewmodel.Studio.HomeStudioViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeStudioView(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeStudioViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier
            .fillMaxSize() // Isi layar penuh
            .background(Color(0xFF0AC4B2))
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeStudio.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getStudio()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp),
                containerColor = Color(0xFF92BCEA)
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add Mahasiswa")
            }
        },
    ) { innerPadding ->
        HomeStudioStatus(
            homeStudioUiState = viewModel.stdUiState,
            retryAction = { viewModel.getStudio() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteStudio(it.id_studio)
                viewModel.getStudio()
            }
        )
    }
}

@Composable
fun HomeStudioStatus(
    homeStudioUiState: HomeStudioUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Studio) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when (homeStudioUiState){
        is HomeStudioUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeStudioUiState.Success ->
            if (homeStudioUiState.studio.isEmpty()){
                Box(
                modifier = modifier.
                fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada data Studio" )
                }
            }else {
                StudioLayout(
                    studio = homeStudioUiState.studio,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_studio)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeStudioUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun OnLoading(
    modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.
        size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.
            ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.
        padding(16.dp))
        Button(onClick =
        retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun StudioLayout(
    studio: List<Studio>,
    modifier: Modifier = Modifier,
    onDeleteClick: (Studio) -> Unit = {},
    onDetailClick: (Studio) -> Unit
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(studio) { studio ->
            StudioCard(
                studio = studio,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(studio) },
                onDeleteClick = { onDeleteClick(studio) }
            )
        }
    }
}

@Composable
fun StudioCard(
    studio: Studio,
    modifier: Modifier = Modifier,
    onDeleteClick: (Studio) -> Unit = {}
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBBDEFB) // Warna biru muda
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = studio.id_studio,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(studio) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = studio.nama_studio,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = studio.kapasitas,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}