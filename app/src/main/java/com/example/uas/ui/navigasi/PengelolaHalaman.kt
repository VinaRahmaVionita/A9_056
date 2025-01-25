package com.example.uas.ui.navigasi

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uas.ui.view.Film.DetailFilmView
import com.example.uas.ui.view.Film.HomeFilmView
import com.example.uas.ui.view.Film.InsertFilmView
import com.example.uas.ui.view.Film.UpdateFilmView
import com.example.uas.ui.view.HomeMenuView
import com.example.uas.ui.view.Penayangan.DetailTayangView
import com.example.uas.ui.view.Penayangan.HomeTayangView
import com.example.uas.ui.view.Penayangan.InsertTayangView
import com.example.uas.ui.view.Penayangan.UpdateTayangView
import com.example.uas.ui.view.Studio.DetailStudioView
import com.example.uas.ui.view.Studio.HomeStudioView
import com.example.uas.ui.view.Studio.InsertStudioView
import com.example.uas.ui.view.Studio.UpdateStudioView
import com.example.uas.ui.view.Tiket.DetailTiketView
import com.example.uas.ui.view.Tiket.HomeTiketView
import com.example.uas.ui.view.Tiket.InsertTiketView
import com.example.uas.ui.view.Tiket.UpdateTiketView

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeMenu.route,
        modifier = Modifier,
    ) {
        //MENU
        composable(DestinasiHomeMenu.route){
            HomeMenuView(
                onFilmClick = { navController.navigate(DestinasiHomeFilm.route) },
                onStudioClick = { navController.navigate(DestinasiHomeStudio.route) },
                onPenayanganClick = { navController.navigate(DestinasiHomePenayangan.route) },
                onTiketClick = { navController.navigate(DestinasiHomeTiket.route) }
            )
        }

        
    }
}