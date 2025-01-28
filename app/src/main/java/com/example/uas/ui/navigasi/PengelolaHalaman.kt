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

        //FILM
        composable(DestinasiHomeFilm.route) {
            HomeFilmView(
                navigateToItemEntry = { navController.navigate(DestinasiInsertFilm.route) },
                onDetailClick = {id_film ->
                    navController.navigate("${DestinasiDetailFilm.route}/$id_film")
                }
            )
        }
        composable(DestinasiInsertFilm.route) {
            InsertFilmView(navigateBack = {
                navController.navigate(DestinasiHomeFilm.route) {
                    popUpTo(DestinasiHomeFilm.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailFilm.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailFilm.id_film){
                    type = NavType.StringType
                }
            )
        ) {
            val id_film = it.arguments?.getString(DestinasiDetailFilm.id_film)
            id_film?.let {
                DetailFilmView(
                    NavigateBack = {
                        navController.navigate(DestinasiHomeFilm.route) {
                            popUpTo(DestinasiHomeFilm.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {
                        navController.navigate("${DestinasiUpdateFilm.route}/$it")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable("${DestinasiUpdateFilm.route}/{id_film}") { navBackStackEntry ->
            val id_film = navBackStackEntry.arguments?.getString("id_film")
            id_film?.let {
                UpdateFilmView(
                    id_film =it,
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }

        //STUDIO
        composable(DestinasiHomeStudio.route) {
            HomeStudioView(
                navigateToItemEntry = { navController.navigate(DestinasiInsertStudio.route) },
                onDetailClick = {id_studio ->
                    navController.navigate("${DestinasiDetailStudio.route}/$id_studio")
                }
            )
        }
        composable(DestinasiInsertStudio.route) {
            InsertStudioView(navigateBack = {
                navController.navigate(DestinasiHomeStudio.route) {
                    popUpTo(DestinasiHomeStudio.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailStudio.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailStudio.id_studio){
                    type = NavType.StringType
                }
            )
        ) {
            val id_studio = it.arguments?.getString(DestinasiDetailStudio.id_studio)
            id_studio?.let {
                DetailStudioView(
                    NavigateBack = {
                        navController.navigate(DestinasiHomeStudio.route) {
                            popUpTo(DestinasiHomeStudio.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {
                        navController.navigate("${DestinasiUpdateStudio.route}/$it")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable("${DestinasiUpdateStudio.route}/{id_studio}") { navBackStackEntry ->
            val id_studio = navBackStackEntry.arguments?.getString("id_studio")
            id_studio?.let {
                UpdateStudioView(
                    id_studio =it,
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }

        //TIKET
        composable(DestinasiHomeTiket.route) {
            HomeTiketView(
                navigateToItemEntry = { navController.navigate(DestinasiInsertTiket.route) },
                onDetailClick = {id_tiket ->
                    navController.navigate("${DestinasiDetailTiket.route}/$id_tiket")
                }
            )
        }
        composable(
            route = "inserttiket/{id_penayangan}",
            arguments = listOf(navArgument("id_penayangan") { type = NavType.StringType })
        ) { backStackEntry ->
            val idPenayangan = backStackEntry.arguments?.getString("id_penayangan") ?: ""
            InsertTiketView(
                id_penayangan = idPenayangan,  // Mengirim id_penayangan ke InsertTiketView
                navigateBack = {
                    navController.navigate(DestinasiHomePenayangan.route) {
                        popUpTo(DestinasiHomePenayangan.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            DestinasiDetailTiket.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailTiket.id_tiket){
                    type = NavType.StringType
                }
            )
        ) {
            val id_tiket = it.arguments?.getString(DestinasiDetailTiket.id_tiket)
            id_tiket?.let {
                DetailTiketView(
                    NavigateBack = {
                        navController.navigate(DestinasiHomeTiket.route) {
                            popUpTo(DestinasiHomeTiket.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick =  {
                        navController.navigate("${DestinasiUpdateTiket.route}/$it")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable("${DestinasiUpdateTiket.route}/{id_tiket}") { navBackStackEntry ->
            val id_tiket = navBackStackEntry.arguments?.getString("id_tiket")
            id_tiket?.let {
                UpdateTiketView(
                    id_tiket =it,
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }

        //PENAYANGAN
        composable(DestinasiHomePenayangan.route) {
            HomeTayangView(
                navigateToItemEntry = { navController.navigate(DestinasiInsertPenayangan.route) },
                onDetailClick = {id_penayangan ->
                    navController.navigate("${DestinasiDetailPenayangan.route}/$id_penayangan")
                }
            )
        }
        composable(DestinasiInsertPenayangan.route) {
            InsertTayangView(navigateBack = {
                navController.navigate(DestinasiHomePenayangan.route) {
                    popUpTo(DestinasiHomePenayangan.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(
            DestinasiDetailPenayangan.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailPenayangan.id_penayangan) {
                    type = NavType.StringType
                }
            )
        ) {
            val id_penayangan = it.arguments?.getString(DestinasiDetailPenayangan.id_penayangan)
            id_penayangan?.let {
                DetailTayangView(
                    NavigateBack = {
                        navController.navigate(DestinasiHomePenayangan.route) {
                            popUpTo(DestinasiHomePenayangan.route) {
                                inclusive = true
                            }
                        }
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdatePenayangan.route}/$it")
                    },
                    onTiketClick = {
                        navController.navigate("${DestinasiInsertTiket.route}/$id_penayangan")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable("${DestinasiUpdatePenayangan.route}/{id_penayangan}") { navBackStackEntry ->
            val id_penayangan = navBackStackEntry.arguments?.getString("id_penayangan")
            id_penayangan?.let {
                UpdateTayangView(
                    id_penayangan =it,
                    onBack = { navController.popBackStack() },
                    onNavigate = { navController.popBackStack() }
                )
            }
        }
    }
}