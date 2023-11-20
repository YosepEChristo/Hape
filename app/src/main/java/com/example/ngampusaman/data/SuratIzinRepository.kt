package com.example.ngampusaman.data

import androidx.lifecycle.LiveData
import com.example.ngampusaman.model.SuratIzin

class SuratIzinRepository(private val suratIzinDao: SuratIzinDao) {
    val readAllSuratIzin: LiveData<List<SuratIzin>> = suratIzinDao.getAll()

    suspend fun insert(suratIzin: SuratIzin) {
        suratIzinDao.insert(suratIzin)
    }

    suspend fun update(suratIzin: SuratIzin) {
        suratIzinDao.update(suratIzin)
    }

    suspend fun delete(suratIzin: SuratIzin) {
        suratIzinDao.delete(suratIzin)
    }
}