package com.example.uas.ui.navigasi

interface DestinasiNavigasi {
    val route: String
    val titleRes: String
}

//MENU
object DestinasiHomeMenu : DestinasiNavigasi {
    override val route = "homemenu"
    override val titleRes = "Home Menu"
}

///FILM
object DestinasiDetailFilm : DestinasiNavigasi {
    override val route = "detailfilm"
    override val titleRes = "Detail Film"
    const val id_film = "id_film"
    val routeWithArgs = "$route/{$id_film}"
}

object DestinasiHomeFilm : DestinasiNavigasi {
    override val route = "homefilm"
    override val titleRes = "Home Film"
}

object DestinasiInsertFilm : DestinasiNavigasi {
    override val route = "insertfilm"
    override val titleRes = "Insert Film"
}

object DestinasiUpdateFilm : DestinasiNavigasi {
    override val route = "updatefilm"
    override val titleRes = "Update Film"
}

