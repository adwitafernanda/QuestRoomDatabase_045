package com.example.pertemuan10.repository

import com.example.pertemuan10.data.entity.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMhs {
    suspend fun insertMhs(mahasiswa: Mahasiswa)
    fun getAllMhs() : Flow<List<Mahasiswa>> //methode untuk memanggil fungsi untuk mendapatkan semua data

    fun getMhs(nim: String) : Flow<Mahasiswa> //mengambil data mahasiswa berdasarkan Nim

    suspend fun deleteMhs(mahasiswa: Mahasiswa) // menghapus data mahasiswa
}