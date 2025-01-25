package com.example.uas.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uas.BioskopApplication
import com.example.uas.ui.viewmodel.Film.DetailFilmViewModel
import com.example.uas.ui.viewmodel.Film.HomeFilmViewModel
import com.example.uas.ui.viewmodel.Film.InsertFilmViewModel
import com.example.uas.ui.viewmodel.Film.UpdateFilmViewModel
import com.example.uas.ui.viewmodel.Studio.DetailStudioViewModel
import com.example.uas.ui.viewmodel.Studio.HomeStudioViewModel
import com.example.uas.ui.viewmodel.Studio.InsertStudioViewModel
import com.example.uas.ui.viewmodel.Studio.UpdateStudioViewModel
import com.example.uas.ui.viewmodel.penayangan.DetailPenayanganViewModel
import com.example.uas.ui.viewmodel.penayangan.HomePenayanganViewModel
import com.example.uas.ui.viewmodel.penayangan.InsertPenayanganViewModel
import com.example.uas.ui.viewmodel.penayangan.UpdatePenayanganViewModel
import com.example.uas.ui.viewmodel.tiket.DetailTiketViewModel
import com.example.uas.ui.viewmodel.tiket.HomeTiketViewModel
import com.example.uas.ui.viewmodel.tiket.InsertTiketViewModel
import com.example.uas.ui.viewmodel.tiket.UpdateTiketViewModel


object PenyediaViewModel {
   @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
   val Factory =
       viewModelFactory {
           //tiket
           initializer {
                HomeFilmViewModel(
                    bioskopApplication().container.filmRepository
                )
           }
           initializer {
               InsertFilmViewModel (
                   bioskopApplication().container.filmRepository
               )
           }
           initializer {
               DetailFilmViewModel (
                   createSavedStateHandle(),
                   bioskopApplication().container.filmRepository
               )
           }
           initializer {
               UpdateFilmViewModel(
                   savedStateHandle = createSavedStateHandle(),
                   repository = bioskopApplication().container.filmRepository
               )
           }
           //studio
           initializer {
               HomeStudioViewModel(
                   bioskopApplication().container.studioRepository
               )
           }
           initializer {
               InsertStudioViewModel (
                   bioskopApplication().container.studioRepository
               )
           }
           initializer {
               DetailStudioViewModel (
                   createSavedStateHandle(),
                   bioskopApplication().container.studioRepository
               )
           }
           initializer {
               UpdateStudioViewModel(
                   savedStateHandle = createSavedStateHandle(),
                   repository = bioskopApplication().container.studioRepository
               )
           }
           //penayangan
           initializer {
               HomePenayanganViewModel(
                   bioskopApplication().container.penayanganRepository
               )
           }
           initializer {
               InsertPenayanganViewModel (
                   bioskopApplication().container.penayanganRepository
               )
           }
           initializer {
               DetailPenayanganViewModel (
                   createSavedStateHandle(),
                   bioskopApplication().container.penayanganRepository
               )
           }
           initializer {
               UpdatePenayanganViewModel(
                   savedStateHandle = createSavedStateHandle(),
                   repository = bioskopApplication().container.penayanganRepository
               )
           }
           //tiket
           initializer {
               HomeTiketViewModel(
                   bioskopApplication().container.tiketRepository
               )
           }
           initializer {
               InsertTiketViewModel (
                   bioskopApplication().container.tiketRepository
               )
           }
           initializer {
               DetailTiketViewModel (
                   createSavedStateHandle(),
                   bioskopApplication().container.tiketRepository
               )
           }
           initializer {
               UpdateTiketViewModel(
                   savedStateHandle = createSavedStateHandle(),
                   repository = bioskopApplication().container.tiketRepository
               )
           }
       }
}

fun CreationExtras.bioskopApplication(): BioskopApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BioskopApplication)