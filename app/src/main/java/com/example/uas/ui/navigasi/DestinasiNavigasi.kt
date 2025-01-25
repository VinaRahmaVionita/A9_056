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

