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

//STUDIO
object DestinasiDetailStudio : DestinasiNavigasi {
    override val route = "detailstudio"
    override val titleRes = "Detail Studio"
    const val id_studio = "id_studio"
    val routeWithArgs = "$route/{$id_studio}"
}

object DestinasiHomeStudio : DestinasiNavigasi {
    override val route = "homestudio"
    override val titleRes = "Home Studio"
}

object DestinasiInsertStudio : DestinasiNavigasi {
    override val route = "insertstudio"
    override val titleRes = "Insert Studio"
}

object DestinasiUpdateStudio : DestinasiNavigasi {
    override val route = "updatestudio"
    override val titleRes = "Update Studio"
}

//PENAYANGAN
object DestinasiDetailPenayangan : DestinasiNavigasi {
    override val route = "detailpenayangan"
    override val titleRes = "Detail Penayangan"
    const val id_penayangan = "id_penayangan"
    val routeWithArgs = "$route/{$id_penayangan}"
}

object DestinasiHomePenayangan : DestinasiNavigasi {
    override val route = "homepenayangan"
    override val titleRes = "Home Penayangan"
}

object DestinasiInsertPenayangan : DestinasiNavigasi {
    override val route = "insertpenayangan"
    override val titleRes = "Insert Penayangan"
}

object DestinasiUpdatePenayangan : DestinasiNavigasi {
    override val route = "updatepenayangan"
    override val titleRes = "Update Penayangan"
}

//TIKET
object DestinasiDetailTiket : DestinasiNavigasi {
    override val route = "detailtiket"
    override val titleRes = "Detail Tiket"
    const val id_tiket = "id_tiket"
    val routeWithArgs = "$route/{$id_tiket}"
}

object DestinasiHomeTiket : DestinasiNavigasi {
    override val route = "hometiket"
    override val titleRes = "Home Tiket"
}

object DestinasiInsertTiket : DestinasiNavigasi {
    override val route = "inserttiket/{id_penayangan}/{id_film}"
    override val titleRes = "Insert Tiket"
}

object DestinasiUpdateTiket : DestinasiNavigasi {
    override val route = "updatetiket"
    override val titleRes = "Update Tiket"
}
