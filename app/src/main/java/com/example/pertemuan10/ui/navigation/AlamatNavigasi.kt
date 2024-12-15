package com.example.pertemuan10.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiInsert : AlamatNavigasi{   //object akan menjadi nama halaman/ menjadi pengenal halaman
    override val route: String = "insert_mhs"
}