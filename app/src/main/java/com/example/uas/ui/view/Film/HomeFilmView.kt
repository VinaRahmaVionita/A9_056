package com.example.uas.ui.view.Film

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas.R
import com.example.uas.model.Film
import com.example.uas.ui.CustomWidget.CostumeTopAppBar
import com.example.uas.ui.navigasi.DestinasiHomeFilm
import com.example.uas.ui.viewmodel.Film.HomeFilmUiState
import com.example.uas.ui.viewmodel.Film.HomeFilmViewModel
import com.example.uas.ui.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeFilmView(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeFilmViewModel = viewModel (factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier
            .fillMaxSize() // Isi layar penuh
            .background(Color(0xFF0AC4B2)) // Warna biru tua (Dark Blue)
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeFilm.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getFilm()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.
                padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.AddCircle, contentDescription = "Add Film")
            }
        },
    ){ innerPadding ->
        HomeFilmStatus(
            homeFilmUiState = viewModel.filmUiState,
            retryAction = { viewModel.getFilm() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteFilm(it.id_film)
                viewModel.getFilm()
            }
        )

    }
}

@Composable
fun HomeFilmStatus(
    homeFilmUiState: HomeFilmUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Film) -> Unit = {},
    onDetailClick: (String) -> Unit
){
    when (homeFilmUiState) {
        is HomeFilmUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeFilmUiState.Success ->
            if (
                homeFilmUiState.film.isEmpty()){
                return
                Box(
                modifier = modifier.
                fillMaxSize(), contentAlignment = Alignment.Center
                ){
                    Text(text = "Tidak ada Film" )
                }
            }else {
                FilmLayout(
                    film = homeFilmUiState.film,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = {
                        onDetailClick(it.id_film)
                    },
                    onDeleteClick = {
                        onDeleteClick(it)
                    }
                )
            }
        is HomeFilmUiState.Error -> OnError(
            retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun OnLoading(
    modifier:
    Modifier = Modifier) {
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
){
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
fun FilmLayout(
    film: List<Film>,
    modifier: Modifier = Modifier,
    onDetailClick: (Film) -> Unit,
    onDeleteClick: (Film) -> Unit = {}
){
   LazyColumn(
       modifier = modifier,
       contentPadding = PaddingValues(16.dp),
       verticalArrangement = Arrangement.spacedBy(16.dp)
   ) {
       items(film){ film ->
           FilmCard(
               film = film,
               modifier = Modifier
                   .fillMaxWidth()
                   .clickable { onDetailClick(film) },
               onDeleteClick = { onDeleteClick(film)
               }
           )
       }
   }
}

@Composable
fun FilmCard(
    film: Film,
    modifier: Modifier = Modifier,
    onDeleteClick: (Film) -> Unit = {}
) {
    var isHovered by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFBBDEFB) // Warna biru muda
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = film.judul_film,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(film) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
                Text(
                    text = film.durasi,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = film.genre,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = film.rating_usia,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
